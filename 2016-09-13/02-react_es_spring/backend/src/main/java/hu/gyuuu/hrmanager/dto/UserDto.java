package hu.gyuuu.hrmanager.dto;

import hu.gyuuu.hrmanager.persistence.bean.User;

public class UserDto
{
	private Long	id;
	private String	name;
	private String	email;

	public UserDto( Long id, String name, String email )
	{
		super();
		this.id = id;
		this.name = name;
		this.email = email;
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

	public String getEmail()
	{
		return email;
	}

	public void setEmail( String email )
	{
		this.email = email;
	}

	public static UserDto createFrom( User user )
	{
		if( user == null )
		{
			return null;
		}
		return new UserDto( user.getId(), user.getDisplayName(), user.getEmail() );
	}

}
