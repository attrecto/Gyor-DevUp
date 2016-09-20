package hu.gyuuu.hrmanager.dto.login;

public class LoginResponseDto
{
	private String token;

	public LoginResponseDto()
	{
	}

	public LoginResponseDto( String token )
	{
		super();
		this.token = token;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken( String token )
	{
		this.token = token;
	}

}
