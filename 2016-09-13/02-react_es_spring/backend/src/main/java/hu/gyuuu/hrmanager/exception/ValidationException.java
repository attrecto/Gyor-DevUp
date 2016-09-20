package hu.gyuuu.hrmanager.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidationException extends ApiException
{
	private static final long			serialVersionUID	= 1L;
	private final Map<String, String>	fieldErrors;
	private final String[]				globalErrors;

	public ValidationException( Map<String, String> fieldErrors, String... globalErrors )
	{
		super( HttpStatus.UNPROCESSABLE_ENTITY );
		this.fieldErrors = fieldErrors;
		this.globalErrors = globalErrors;
	}

	public ValidationException( Map<String, String> fieldErrors )
	{
		super( HttpStatus.UNPROCESSABLE_ENTITY );
		this.fieldErrors = fieldErrors;
		this.globalErrors = null;
	}

	public ValidationException( String... globalErrors )
	{
		super( HttpStatus.UNPROCESSABLE_ENTITY );
		this.fieldErrors = null;
		this.globalErrors = globalErrors;
	}

	@JsonProperty
	public Map<String, String> getFieldErrors()
	{
		return fieldErrors;
	}

	@JsonProperty
	public String[] getGlobalErrors()
	{
		return globalErrors;
	}

}
