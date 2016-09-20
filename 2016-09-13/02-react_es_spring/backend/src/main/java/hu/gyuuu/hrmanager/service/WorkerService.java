package hu.gyuuu.hrmanager.service;

import hu.gyuuu.hrmanager.dto.WorkerDto;
import hu.gyuuu.hrmanager.dto.company.CreateWorkerDto;
import hu.gyuuu.hrmanager.dto.company.UpdateWorkerDto;
import hu.gyuuu.hrmanager.dto.workerreservation.DecideWorkerReservation;
import hu.gyuuu.hrmanager.dto.workerreservation.ReserveWorkerDto;
import hu.gyuuu.hrmanager.dto.wrapper.CollectionWrapper;

public interface WorkerService {

    CollectionWrapper<WorkerDto> findWorkersBy(Long companyId);

    WorkerDto findWorker(Long workerId);

    Long createWorker(Long companyId, CreateWorkerDto dto);

    void updateWorker(Long workerId, UpdateWorkerDto dto);

    void deleteWorker(Long workerId);

    void reserveWorker(Long workerId, ReserveWorkerDto dto);

    void decide(DecideWorkerReservation dto);

}
