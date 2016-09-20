package hu.gyuuu.hrmanager.test;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

import org.junit.Before;
import org.junit.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import hu.gyuuu.hrmanager.security.jwt.JWTFilter;

@Rollback(true)
public abstract class DocumentationTest extends BaseSpringTest
{
	@Rule
	public JUnitRestDocumentation	restDocumentation	= new JUnitRestDocumentation( "target/generated-snippets" );

	@Autowired
	private WebApplicationContext	context;

	@Autowired
	protected ObjectMapper			objectMapper;

	@Autowired
	protected JWTFilter				filter;

	protected MockMvc				mockMvc;

	protected final String			HEADER_AUTH			= "Authorization";

	@Before
	public void setUp()
	{
		this.mockMvc = MockMvcBuilders.webAppContextSetup( this.context ).apply( springSecurity() )
				.apply( documentationConfiguration( this.restDocumentation ).uris().withHost( "devup.gyuuu.hu" ) )
				.build();
	}
}
