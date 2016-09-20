package hu.gyuuu.hrmanager.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JWTFilter extends GenericFilterBean
{

	private static final String AUTH_HEADER = "Authorization";
    private static final String	AUTH_SCHEMA	= "Bearer";
	private static final Log	LOG			= LogFactory.getLog( JWTFilter.class );
	private final JWTFactory	jwtFactory;

	public JWTFilter( JWTFactory jwtFactory )
	{
		this.jwtFactory = jwtFactory;
	}

	@Override
	public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain )
			throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		String stringToken = req.getHeader( AUTH_HEADER );
		if( stringToken != null )
		{
			// remove schema from token
			if( stringToken.startsWith( AUTH_SCHEMA ) )
			{

				stringToken = stringToken.substring( AUTH_SCHEMA.length() ).trim();
				try
				{
					Authentication authentication = jwtFactory.validateToken( stringToken );
					SecurityContextHolder.getContext().setAuthentication( authentication );
				}
				catch( AuthenticationException e )
				{
					LOG.debug( String.format( "Unsuccessfull authentication: IP Address: %s, reason: %s",
							req.getRemoteAddr(), e.getMessage() ) );
				}
				catch( Exception e )
				{
					LOG.error( "Error occured during JWT Authentication", e );
				}
			}
		}
		chain.doFilter( request, response );
	}

}
