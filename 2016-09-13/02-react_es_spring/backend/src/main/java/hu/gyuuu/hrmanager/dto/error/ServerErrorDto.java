package hu.gyuuu.hrmanager.dto.error;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ServerErrorDto
{
	@JsonIgnore
	private HttpStatus	status;
	private Long		serialNumber;
	private String		message;

	public ServerErrorDto( HttpStatus status, Long serialNumber, String message )
	{
		super();
		this.status = status;
		this.serialNumber = serialNumber;
		this.message = message;
	}

	public HttpStatus getStatus()
	{
		return status;
	}

	public void setStatus( HttpStatus status )
	{
		this.status = status;
	}

	public Long getSerialNumber()
	{
		return serialNumber;
	}

	public void setSerialNumber( Long serialNumber )
	{
		this.serialNumber = serialNumber;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage( String message )
	{
		this.message = message;
	}

	@JsonProperty
	public Integer getStatusCode()
	{
		return status.value();
	}

}
