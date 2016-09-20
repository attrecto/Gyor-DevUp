package hu.gyuuu.hrmanager.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import hu.gyuuu.hrmanager.security.RESTAuthenticationEntryPoint;
import hu.gyuuu.hrmanager.security.HRManagerAuthenticationProvider;
import hu.gyuuu.hrmanager.security.enums.Authority;
import hu.gyuuu.hrmanager.security.jwt.JWTFactory;
import hu.gyuuu.hrmanager.security.jwt.JWTFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Order(2)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private JWTFactory						jwtFactory;
	@Autowired
	private RESTAuthenticationEntryPoint	restEntryPoint;
	@Autowired
	private HRManagerAuthenticationProvider		hrAuthProvider;

	@Override
	protected void configure( HttpSecurity http ) throws Exception
	{
		//@formatter:off
		http.authorizeRequests()
			.antMatchers( HttpMethod.POST, "/api/login" ).permitAll()
			.antMatchers( HttpMethod.POST, "/api/signup" ).permitAll()
			.antMatchers( HttpMethod.GET, "/api/registrations/pending" ).hasAuthority( Authority.APPROVE_USER.name() )
			.antMatchers( HttpMethod.POST, "/api/registrations/pending/decide" ).hasAuthority( Authority.APPROVE_USER.name() )
			.antMatchers( HttpMethod.GET, "/api/users/me" ).hasAnyAuthority( Authority.VIEW_SELF.name(), Authority.VIEW_USERS.name())
			.antMatchers( HttpMethod.GET, "/api/users/*" ).hasAuthority( Authority.VIEW_USERS.name())
			.antMatchers( HttpMethod.GET, "/api/companies" ).hasAuthority( Authority.VIEW_COMPANIES.name())
			.antMatchers( HttpMethod.GET, "/api/companies/*/workers" ).hasAuthority( Authority.VIEW_COMPANIES.name())
			.antMatchers( HttpMethod.GET, "/api/companies/*/workers/*" ).hasAuthority( Authority.VIEW_WORKERS.name())
			.antMatchers( HttpMethod.POST, "/api/companies/*/workers" ).hasAnyAuthority( Authority.MANAGE_ANY_WORKER.name(), Authority.MANAGE_OWN_WORKER.name())
			.antMatchers( HttpMethod.PUT, "/api/companies/*/workers/*" ).hasAnyAuthority( Authority.MANAGE_ANY_WORKER.name(), Authority.MANAGE_OWN_WORKER.name())
			.antMatchers( HttpMethod.DELETE, "/api/companies/*/workers/*" ).hasAnyAuthority( Authority.MANAGE_ANY_WORKER.name(), Authority.MANAGE_OWN_WORKER.name())
			.antMatchers( HttpMethod.POST, "/api/companies/*/workers/*/reserve" ).hasAnyAuthority( Authority.RESERVE_WORKER.name())
			.antMatchers( HttpMethod.POST, "/api/workerReservations/decide" ).hasAnyAuthority( Authority.DECIDE_OWN_WORKER_RESERVATION.name())
			.anyRequest().denyAll()
		.and().sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS )
		.and().exceptionHandling().authenticationEntryPoint(restEntryPoint )
		.and().addFilterBefore( jwtFilter(), UsernamePasswordAuthenticationFilter.class )
		.csrf().disable()
		;
		//@formatter:on
	}

	@Override
	protected void configure( AuthenticationManagerBuilder auth ) throws Exception
	{
		auth.authenticationProvider( hrAuthProvider );
	}

	@Bean
	public JWTFilter jwtFilter()
	{
		return new JWTFilter( jwtFactory );
	}

}
