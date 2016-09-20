package hu.gyuuu.hrmanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.gyuuu.hrmanager.dto.login.LoginDto;
import hu.gyuuu.hrmanager.dto.login.LoginResponseDto;
import hu.gyuuu.hrmanager.security.AuthenticationDetails;
import hu.gyuuu.hrmanager.security.HRManagerAuthenticationToken;
import hu.gyuuu.hrmanager.security.jwt.JWTFactory;
import hu.gyuuu.hrmanager.service.AuthenticationService;

@Service
@Transactional(readOnly=true)
public class AuthenticationServiceImpl implements AuthenticationService
{
	private AuthenticationProvider authProvider;
	private JWTFactory jwtFactory;
	
	@Autowired
	public AuthenticationServiceImpl( AuthenticationProvider authProvider, JWTFactory jwtFactory )
	{
		super();
		this.authProvider = authProvider;
		this.jwtFactory = jwtFactory;
	}
	
	@Override
	public LoginResponseDto login(LoginDto dto){
		Authentication auth = new HRManagerAuthenticationToken( dto.getUsername(), dto.getPassword(), dto.getCompany() );
		auth = authProvider.authenticate( auth );
		AuthenticationDetails authDetails = (AuthenticationDetails) auth.getDetails();
		String token = jwtFactory.generateToken( authDetails, auth.getAuthorities() );
		return new LoginResponseDto( token );
	}

}
