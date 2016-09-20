package hu.gyuuu.hrmanager.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import hu.gyuuu.hrmanager.security.jwt.JWTFactory;

@Configuration
@EnableConfigurationProperties
@Order(1)
public class AppConfig
{

	@Bean
	public LocaleResolver localeResolver(){
	    AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
	    return resolver;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
	    return new BCryptPasswordEncoder();
	}
	
	@Autowired
	@Bean
	public JWTFactory jwtFactory(JWTConfiguration config){
		return new JWTFactory( config );
	}

}
