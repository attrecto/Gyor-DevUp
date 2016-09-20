package hu.gyuuu.hrmanager.service;

import hu.gyuuu.hrmanager.dto.login.LoginDto;
import hu.gyuuu.hrmanager.dto.login.LoginResponseDto;

public interface AuthenticationService
{

	LoginResponseDto login( LoginDto dto );

}
