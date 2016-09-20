package hu.gyuuu.hrmanager.controlleradvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import hu.gyuuu.hrmanager.dto.error.ServerErrorDto;
import hu.gyuuu.hrmanager.exception.ApiException;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice
{

	private static Log LOG = LogFactory.getLog( ExceptionHandlerControllerAdvice.class );

	@ExceptionHandler(ApiException.class)
	public @ResponseBody ApiException handleApiException( ApiException ex, HttpServletResponse response )
	{
		response.setStatus( ex.getStatusCode() );
		return ex;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ServerErrorDto handleUnexpectedException( Exception e )
	{
		Long serialNumber = System.currentTimeMillis();
		LOG.error( "serialNumber: " + serialNumber, e );
		return new ServerErrorDto( HttpStatus.INTERNAL_SERVER_ERROR, serialNumber, e.getMessage() );
	}

	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	public @ResponseBody ServerErrorDto handleUnsupportedMediaTypeExceptio( HttpMediaTypeNotAcceptableException e )
	{
		Long serialNumber = System.currentTimeMillis();
		LOG.debug( "serialNumber: " + serialNumber, e );
		return new ServerErrorDto( HttpStatus.UNSUPPORTED_MEDIA_TYPE, serialNumber, e.getMessage() );
	}

	@ExceptionHandler(
	{ MissingServletRequestParameterException.class, HttpMessageNotReadableException.class,
			ServletRequestBindingException.class, MethodArgumentNotValidException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ServerErrorDto handleBadRequestException( Exception e )
	{
		Long serialNumber = System.currentTimeMillis();
		LOG.debug( "serialNumber: " + serialNumber, e );
		return new ServerErrorDto( HttpStatus.BAD_REQUEST, serialNumber, e.getMessage() );
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public @ResponseBody ServerErrorDto handleMethodNotSupporteddException( HttpRequestMethodNotSupportedException e )
	{
		Long serialNumber = System.currentTimeMillis();
		LOG.error( "serialNumber: " + serialNumber, e );
		return new ServerErrorDto( HttpStatus.METHOD_NOT_ALLOWED, serialNumber, e.getMessage() );
	}

	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public @ResponseBody ServerErrorDto handleAuthException( HttpServletRequest request, AuthenticationException e )
	{
		Long serialNumber = System.currentTimeMillis();
		LOG.debug( String.format( "Unsuccessfull authentication from: %s with reason: %s serialNumber: %d",
				request.getRemoteAddr(), e.getMessage(), serialNumber ) );
		return new ServerErrorDto( HttpStatus.UNAUTHORIZED, serialNumber, "login.failure" );
	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public @ResponseBody ServerErrorDto handleAccessDeniedException( AccessDeniedException e )
	{
		Long serialNumber = System.currentTimeMillis();
		LOG.debug( "Access Denied, serialNumber: " + serialNumber, e );
		return new ServerErrorDto( HttpStatus.FORBIDDEN, serialNumber, "Access Denied" );
	}

}
