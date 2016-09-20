package hu.gyuuu.hrmanager.dto;

import hu.gyuuu.hrmanager.dto.registration.ContactPersonDto;
import hu.gyuuu.hrmanager.persistence.bean.Company;

public class CompanyDto
{
	private Long				id;
	private String				name;
	private String				identifier;
	private ContactPersonDto	contact;

	public CompanyDto( Long id, String name, String identifier, ContactPersonDto contact )
	{
		super();
		this.id = id;
		this.name = name;
		this.identifier = identifier;
		this.contact = contact;
	}

	public Long getId()
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

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

	public static CompanyDto createFrom( Company company )
	{
		if( company == null )
		{
			return null;
		}
		ContactPersonDto contact = ContactPersonDto.createFrom( company.getContact() );
		return new CompanyDto( company.getId(), company.getName(), company.getIdentifier(), contact );
	}

}
