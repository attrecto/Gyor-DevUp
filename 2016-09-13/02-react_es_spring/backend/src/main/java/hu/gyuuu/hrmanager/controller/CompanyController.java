package hu.gyuuu.hrmanager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hu.gyuuu.hrmanager.dto.CompanyDto;
import hu.gyuuu.hrmanager.dto.WorkerDto;
import hu.gyuuu.hrmanager.dto.company.CreateWorkerDto;
import hu.gyuuu.hrmanager.dto.company.UpdateWorkerDto;
import hu.gyuuu.hrmanager.dto.workerreservation.ReserveWorkerDto;
import hu.gyuuu.hrmanager.dto.wrapper.CollectionWrapper;
import hu.gyuuu.hrmanager.service.CompanyService;
import hu.gyuuu.hrmanager.service.WorkerService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController extends BaseController {

    private WorkerService workerService;
    private CompanyService companyService;

    @Autowired
    public CompanyController(WorkerService workerService, CompanyService companyService) {
        super();
        this.workerService = workerService;
        this.companyService = companyService;
    }
    
    @RequestMapping(value="", method=RequestMethod.GET)
    public CollectionWrapper<CompanyDto> findCompanies(){
        return companyService.findCompanies();
    }

    @RequestMapping(value="/{companyId}/workers", method=RequestMethod.GET)
    public CollectionWrapper<WorkerDto> findWorkers(@PathVariable("companyId") Long companyId) {
        return workerService.findWorkersBy(companyId);
    }
    
    @RequestMapping(value="/{companyId}/workers/{workerId}", method=RequestMethod.GET)
    public WorkerDto findWorker(@PathVariable("workerId") Long workerId){
        return workerService.findWorker(workerId);
    }
    
    @RequestMapping(value="/{companyId}/workers", method=RequestMethod.POST)
    public Long createWorker(@PathVariable("companyId") Long companyId, @Valid @RequestBody CreateWorkerDto dto, BindingResult bindingResult){
        validateBindingResult(bindingResult);
        return workerService.createWorker(companyId, dto);
    }
    
    @RequestMapping(value="/{companyId}/workers/{workerId}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateWorker(@PathVariable("workerId") Long workerId, @Valid @RequestBody UpdateWorkerDto dto, BindingResult bindingResult){
        validateBindingResult(bindingResult);
        workerService.updateWorker(workerId, dto);
    }
    
    @RequestMapping(value="/{companyId}/workers/{workerId}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorker(@PathVariable("workerId") Long workerId){
        workerService.deleteWorker(workerId);
    }
    
    @RequestMapping(value="/{companyId}/workers/{workerId}/reserve", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void reserveWorker(@PathVariable("workerId") Long workerId, @Valid @RequestBody ReserveWorkerDto dto, BindingResult bindingResult){
        validateBindingResult(bindingResult);
        workerService.reserveWorker(workerId, dto);
    }
    
}
