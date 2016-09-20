package hu.gyuuu.hrmanager.exception;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ApiException extends RuntimeException
{

	private static final long	serialVersionUID	= 1L;
	private final HttpStatus	status;

	public ApiException( HttpStatus status )
	{
		super();
		this.status = status;
	}

	@JsonProperty
	public Integer getStatusCode()
	{
		return status.value();
	}
}
