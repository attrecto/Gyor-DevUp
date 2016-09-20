package hu.gyuuu.hrmanager.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import hu.gyuuu.hrmanager.exception.ValidationException;


public class BaseController
{
	public void validateBindingResult( BindingResult bindingResult ) throws ValidationException
	{
		if( bindingResult.hasErrors() )
		{
			Map<String, String> fieldErrors = new HashMap<>();
			for( FieldError fieldError : bindingResult.getFieldErrors() )
			{
				String field = fieldError.getField();
				String error = fieldError.getCode();
				fieldErrors.put( field, error );
			}
			throw new ValidationException( fieldErrors );
		}
	}
}
