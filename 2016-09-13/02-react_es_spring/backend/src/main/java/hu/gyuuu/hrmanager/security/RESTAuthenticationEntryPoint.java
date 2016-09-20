package hu.gyuuu.hrmanager.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class RESTAuthenticationEntryPoint implements AuthenticationEntryPoint
{

	public RESTAuthenticationEntryPoint()
	{

	}

	@Override
	public void commence( HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException ) throws IOException, ServletException
	{
		response.setStatus( HttpStatus.UNAUTHORIZED.value() );
	}

}
