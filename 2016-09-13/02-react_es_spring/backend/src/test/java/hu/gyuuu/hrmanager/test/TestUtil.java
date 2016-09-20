package hu.gyuuu.hrmanager.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import hu.gyuuu.hrmanager.security.AuthenticationDetails;
import hu.gyuuu.hrmanager.security.enums.Authority;
import hu.gyuuu.hrmanager.security.jwt.JWTFactory;

@Component
public class TestUtil
{
	private static final String AUTH_SCHEMA = "Bearer ";
	private ResourceLoader resourceLoader;
	private JWTFactory	jwtFactory;

	@Autowired
	public TestUtil( ResourceLoader resourceLoader, JWTFactory jwtFactory )
	{
		super();
		this.resourceLoader = resourceLoader;
		this.jwtFactory = jwtFactory;
	}

	public String getTestData( String testName, String resourceName )
	{
		String url = String.format( "classpath:%s/%s", testName, resourceName );
		Resource res = resourceLoader.getResource( url );
		try
		{
			List<String> lines = FileUtils.readLines( res.getFile(), "UTF-8" );
			StringBuilder builder = new StringBuilder();
			lines.stream().forEach( s -> builder.append( s ) );
			return builder.toString();
		}
		catch( IOException e )
		{
			throw new RuntimeException( "cant load file: " + url, e );
		}
	}
	
	public String getAuthHeader(TestUser user, Authority... authorities){
		AuthenticationDetails authDetails = new AuthenticationDetails( user.getUserId(), user.getName(), user.getCompanyId() );
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for(Authority authority : authorities){
			grantedAuthorities.add(new SimpleGrantedAuthority(authority.name()));
		}
		String token = jwtFactory.generateToken( authDetails, grantedAuthorities );
		return AUTH_SCHEMA+token;
	}
}
