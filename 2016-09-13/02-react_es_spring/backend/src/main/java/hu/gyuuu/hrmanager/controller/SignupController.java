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

import hu.gyuuu.hrmanager.dto.registration.RegistrationDto;
import hu.gyuuu.hrmanager.service.SignupService;

@RequestMapping("/api/signup")
@RestController
public class SignupController extends BaseController
{
	private SignupService signupService;

	@Autowired
	public SignupController( SignupService signupService )
	{
		super();
		this.signupService = signupService;
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value="", method=RequestMethod.POST)
	public void register(@Valid @RequestBody RegistrationDto dto, BindingResult bindingResult){
		validateBindingResult( bindingResult );
		signupService.register( dto );
	}
}
