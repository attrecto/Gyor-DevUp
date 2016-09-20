package hu.gyuuu.hrmanager.dto.pendingregistrations;

import hu.gyuuu.hrmanager.dto.CompanyDto;
import hu.gyuuu.hrmanager.dto.UserDto;

public class PendingRegistrationDto
{
	private UserDto		user;
	private CompanyDto	company;

	public PendingRegistrationDto( UserDto user, CompanyDto company )
	{
		super();
		this.user = user;
		this.company = company;
	}

	public UserDto getUser()
	{
		return user;
	}

	public void setUser( UserDto user )
	{
		this.user = user;
	}

	public CompanyDto getCompany()
	{
		return company;
	}

	public void setCompany( CompanyDto company )
	{
		this.company = company;
	}

}
