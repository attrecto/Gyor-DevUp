package hu.gyuuu.hrmanager.test.learning;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import hu.gyuuu.hrmanager.test.BaseSpringTest;

public class PasswordEncoderTest extends BaseSpringTest
{
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	public void encodePassword(){
		String encodedPassword = passwordEncoder.encode( "e7348" );
		System.out.println( encodedPassword );
	}
}
