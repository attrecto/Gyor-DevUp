package hu.gyuuu.hrmanager.dto.registration;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import hu.gyuuu.hrmanager.persistence.bean.ContactPerson;

/**
 * @author gpusker
 *
 */
public class ContactPersonDto
{
	@NotBlank
	private String	name;
	private String	phone;
	@NotBlank
	@Email
	private String	email;

	public ContactPersonDto()
	{
	}

	public ContactPersonDto( String name, String phone, String email )
	{
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone( String phone )
	{
		this.phone = phone;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail( String email )
	{
		this.email = email;
	}

	public static ContactPersonDto createFrom( ContactPerson contactPerson )
	{
		if(contactPerson == null){
			return null;
		}
		return new ContactPersonDto( contactPerson.getName(), contactPerson.getPhone(), contactPerson.getEmail() );
	}

}
