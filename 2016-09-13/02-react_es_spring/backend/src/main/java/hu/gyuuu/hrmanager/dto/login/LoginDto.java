package hu.gyuuu.hrmanager.dto.login;

import org.hibernate.validator.constraints.NotBlank;

public class LoginDto
{
	private String	company;
	@NotBlank
	private String	username;
	@NotBlank
	private String	password;

	public String getCompany()
	{
		return company;
	}

	public void setCompany( String company )
	{
		this.company = company;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername( String username )
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword( String password )
	{
		this.password = password;
	}

}
