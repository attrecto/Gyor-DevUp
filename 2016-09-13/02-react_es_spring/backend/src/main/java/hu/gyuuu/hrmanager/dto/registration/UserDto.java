package hu.gyuuu.hrmanager.dto.registration;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class UserDto
{
	@NotBlank
	private String	name;
	@NotBlank
	@Email
	private String	email;
	@NotBlank
	@Length(min=4)
	private String	password;

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail( String email )
	{
		this.email = email;
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
