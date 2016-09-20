package hu.gyuuu.hrmanager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.gyuuu.hrmanager.dto.login.LoginDto;
import hu.gyuuu.hrmanager.dto.login.LoginResponseDto;
import hu.gyuuu.hrmanager.service.AuthenticationService;

@RequestMapping("/api/login")
@RestController
public class AuthenticationController extends BaseController
{
	private AuthenticationService authenticationService;

	@Autowired
	public AuthenticationController( AuthenticationService authenticationService )
	{
		super();
		this.authenticationService = authenticationService;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public LoginResponseDto login( @Valid @RequestBody LoginDto dto, BindingResult bindingResult )
	{
		validateBindingResult( bindingResult );
		return authenticationService.login( dto );
	}
}
