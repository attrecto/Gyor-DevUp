package hu.gyuuu.hrmanager.security.jwt;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import hu.gyuuu.hrmanager.security.AuthenticationDetails;

public class JWTAuthenticationToken extends AbstractAuthenticationToken
{
	private static final long	serialVersionUID	= 7913543925355175077L;
	private Long				userId;
	private String				token;

	public JWTAuthenticationToken( Long userId, String token, Collection<? extends GrantedAuthority> authorities, AuthenticationDetails authDetails )
	{
		super( authorities );
		this.userId = userId;
		this.token = token;
		setDetails(authDetails);
		setAuthenticated(true);
	}

	@Override
	public String getCredentials()
	{
		return token;
	}

	@Override
	public Long getPrincipal()
	{
		return userId;
	}

	public AuthenticationDetails getAuthenticationDetails(){
	    return (AuthenticationDetails) getDetails();
	}
}
