package hu.gyuuu.hrmanager.dto.registration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class CompanyDto
{
	@NotBlank
	private String				name;
	@NotBlank
	@Length(min = 5)
	private String				identifier;
	@NotNull
	@Valid
	private ContactPersonDto	contact;

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getIdentifier()
	{
		return identifier;
	}

	public void setIdentifier( String identifier )
	{
		this.identifier = identifier;
	}

	public ContactPersonDto getContact()
	{
		return contact;
	}

	public void setContact( ContactPersonDto contact )
	{
		this.contact = contact;
	}

}
