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

import hu.gyuuu.hrmanager.dto.pendingregistrations.DecideUserApprovalDto;
import hu.gyuuu.hrmanager.dto.pendingregistrations.PendingRegistrationDto;
import hu.gyuuu.hrmanager.dto.wrapper.CollectionWrapper;
import hu.gyuuu.hrmanager.service.PendingRegistrationService;

@RequestMapping("/api/registrations/pending")
@RestController
public class PendingRegistrationController extends BaseController
{
	private PendingRegistrationService pendingRegService;

	@Autowired
	public PendingRegistrationController( PendingRegistrationService pendingRegService )
	{
		super();
		this.pendingRegService = pendingRegService;
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public CollectionWrapper<PendingRegistrationDto> findPendingRegistrations(){
		return pendingRegService.findPendingRegistrations();
	}
	
	@RequestMapping(value="/decide", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void decideUserApproval(@Valid @RequestBody DecideUserApprovalDto dto, BindingResult bindingResult){
		validateBindingResult( bindingResult );
		pendingRegService.decide( dto );
	}

}
