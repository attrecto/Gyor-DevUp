package hu.gyuuu.hrmanager.dto.registration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class RegistrationDto
{
	@NotNull
	@Valid
	private UserDto		user;
	@NotNull
	@Valid
	private CompanyDto	company;

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
