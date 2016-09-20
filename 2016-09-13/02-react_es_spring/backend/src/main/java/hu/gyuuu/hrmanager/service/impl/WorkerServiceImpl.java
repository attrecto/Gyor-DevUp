package hu.gyuuu.hrmanager.service.impl;

import static hu.gyuuu.hrmanager.ErrorKeys.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.gyuuu.hrmanager.dto.WorkerDto;
import hu.gyuuu.hrmanager.dto.company.CreateWorkerDto;
import hu.gyuuu.hrmanager.dto.company.UpdateWorkerDto;
import hu.gyuuu.hrmanager.dto.workerreservation.DecideWorkerReservation;
import hu.gyuuu.hrmanager.dto.workerreservation.ReserveWorkerDto;
import hu.gyuuu.hrmanager.dto.workerreservation.DecideWorkerReservation.Decision;
import hu.gyuuu.hrmanager.dto.wrapper.CollectionWrapper;
import hu.gyuuu.hrmanager.enums.ReservationStatus;
import hu.gyuuu.hrmanager.exception.ValidationException;
import hu.gyuuu.hrmanager.persistence.bean.Company;
import hu.gyuuu.hrmanager.persistence.bean.Worker;
import hu.gyuuu.hrmanager.persistence.bean.WorkerReservation;
import hu.gyuuu.hrmanager.persistence.repository.CompanyRepository;
import hu.gyuuu.hrmanager.persistence.repository.WorkerRepository;
import hu.gyuuu.hrmanager.persistence.repository.WorkerReservationRepository;
import hu.gyuuu.hrmanager.security.AuthenticationDetails;
import hu.gyuuu.hrmanager.service.WorkerService;

@Service
@Transactional(readOnly = true)
public class WorkerServiceImpl implements WorkerService {

    private CompanyRepository           companyRepo;
    private WorkerRepository            workerRepo;
    private WorkerReservationRepository workerReservationRepo;

    @Autowired
    public WorkerServiceImpl(CompanyRepository companyRepo, WorkerRepository workerRepo,
            WorkerReservationRepository workerReservationRepo) {
        super();
        this.companyRepo = companyRepo;
        this.workerRepo = workerRepo;
        this.workerReservationRepo = workerReservationRepo;
    }

    @Override
    @PreAuthorize("hasAuthority('VIEW_COMPANIES')")
    public CollectionWrapper<WorkerDto> findWorkersBy(Long companyId) {
        Company company = validateCompany(companyId);
        return createResult(company);
    }

    private CollectionWrapper<WorkerDto> createResult(Company company) {
        Collection<Worker> workers = workerRepo.findByCompany(company);
        List<WorkerDto> workerDtos = new ArrayList<>();
        workers.forEach(worker -> {
            workerDtos.add(WorkerDto.createFrom(worker));
        });
        return new CollectionWrapper<>(workerDtos);
    }

    private Company validateCompany(Long companyId) {
        Company company = companyRepo.findOne(companyId);
        if (company == null) {
            throw new ValidationException(COMPANY_NOT_EXISTS);
        }
        return company;
    }

    @Override
    @PreAuthorize("hasAuthority('VIEW_WORKERS')")
    public WorkerDto findWorker(Long workerId) {
        Worker worker = validateWorker(workerId);
        return createResult(worker);
    }

    private WorkerDto createResult(Worker worker) {
        return WorkerDto.createFrom(worker);
    }

    private Worker validateWorker(Long workerId) {
        Worker worker = workerRepo.findOne(workerId);
        if (worker == null) {
            throw new ValidationException(WORKER_NOT_EXISTS);
        }
        return worker;
    }

    @Override
    @Transactional(readOnly = false)
    @PreAuthorize("hasPermission(#companyId, 'manageWorkers')")
    public Long createWorker(Long companyId, CreateWorkerDto dto) {
        Company company = validateCompany(companyId);
        return doCreateWorker(dto, company);
    }

    private Long doCreateWorker(CreateWorkerDto dto, Company company) {
        Worker worker = new Worker(dto.getName(), dto.getBiography(), company);
        company.getWorkers().add(worker);
        workerRepo.flush();
        return worker.getId();
    }

    @Override
    @Transactional(readOnly = false)
    @PreAuthorize("hasPermission(#workerId, 'manageWorker')")
    public void updateWorker(Long workerId, UpdateWorkerDto dto) {
        Worker worker = validateWorker(workerId);
        doUpdateWorker(dto, worker);
    }

    private void doUpdateWorker(UpdateWorkerDto dto, Worker worker) {
        worker.setName(dto.getName());
        worker.setBiography(dto.getBiography());
    }

    @Override
    @Transactional(readOnly = false)
    @PreAuthorize("hasPermission(#workerId, 'manageWorker')")
    public void deleteWorker(Long workerId) {
        Worker worker = validateWorker(workerId);
        validateWorkerRelations(worker);
        doDeleteWorker(worker);
    }

    private void validateWorkerRelations(Worker worker) {
        if(!worker.getReservations().isEmpty()){
            throw new ValidationException(WORKER_HAS_RESERVATIONS);
        }
    }

    private void doDeleteWorker(Worker worker) {
        workerRepo.delete(worker);
    }

    @Override
    @Transactional(readOnly = false)
    @PreAuthorize("hasAuthority('RESERVE_WORKER')")
    public void reserveWorker(Long workerId, ReserveWorkerDto dto) {
        Worker worker = validateWorker(workerId);
        validateDateRange(dto);
        lockWorker(workerId);
        validateRangeConflicts(worker, dto);
        Company bookingCompany = getBookingCompany();
        doReserveWorker(dto, worker, bookingCompany);
    }

    private void doReserveWorker(ReserveWorkerDto dto, Worker worker, Company bookingCompany) {
        WorkerReservation workerReservation = new WorkerReservation(worker, bookingCompany, null, dto.getStartDate(), dto.getEndDate());
        workerReservationRepo.save(workerReservation);
    }

    private Company getBookingCompany() {
        AuthenticationDetails authDetails = (AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        Company bookingCompany = companyRepo.findOne(authDetails.getCompanyId());
        return bookingCompany;
    }

    private void validateRangeConflicts(Worker worker, ReserveWorkerDto dto) {
        List<WorkerReservation> interserctions = workerReservationRepo.findByIntersectingDateRange(worker, dto.getStartDate(), dto.getEndDate());
        if(!interserctions.isEmpty()){
            throw new ValidationException(WORKER_RESERVATION_CONFLICTING_RESERVATION);
        }
    }

    private void lockWorker(Long workerId) {
        workerRepo.findAndLockOne(workerId);
    }

    private void validateDateRange(ReserveWorkerDto dto) {
        Map<String, String> fieldErrors = new HashMap<>();
        if (!dto.getStartDate().before(dto.getEndDate())) {
            fieldErrors.put("endDate", WORKER_RESERVATION_INVALID_END_DATE);
        }
        if (!fieldErrors.isEmpty()) {
            throw new ValidationException(fieldErrors);
        }
    }
    
    @Override
    @Transactional(readOnly = false)
    public void decide(DecideWorkerReservation dto){
        WorkerReservation workerReservation = workerReservationRepo.findOne(dto.getReservationId());
        if(workerReservation == null){
            throw new ValidationException(WORKER_RESERVATION_NOT_EXISTS);
        }
        if(workerReservation.getStatus() != null){
            throw new ValidationException(WORKER_RESERVATION_ALREADY_DECIDED);
        }
        workerReservation.setStatus(dto.getDecision() == Decision.APPROVE?ReservationStatus.APPROVED:ReservationStatus.REJECTED);
   }
}
