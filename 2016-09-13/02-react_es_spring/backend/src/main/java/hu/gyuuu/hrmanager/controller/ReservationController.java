package hu.gyuuu.hrmanager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hu.gyuuu.hrmanager.dto.workerreservation.DecideWorkerReservation;
import hu.gyuuu.hrmanager.service.WorkerService;

@RestController
@RequestMapping("/api/workerReservations")
public class ReservationController extends BaseController {

    private WorkerService workerService;

    @Autowired
    public ReservationController(WorkerService workerService) {
        super();
        this.workerService = workerService;
    }
    
    @RequestMapping(value="/decide", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void reserveWorker( @Valid @RequestBody DecideWorkerReservation dto, BindingResult bindingResult){
        validateBindingResult(bindingResult);
        workerService.decide(dto);
    }
    
    
}
