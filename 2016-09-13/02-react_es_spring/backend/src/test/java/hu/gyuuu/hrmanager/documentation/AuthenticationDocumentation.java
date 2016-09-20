package hu.gyuuu.hrmanager.documentation;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import hu.gyuuu.hrmanager.test.DocumentationTest;

public class AuthenticationDocumentation extends DocumentationTest
{
	private static final String TEST_NAME = AuthenticationDocumentation.class.getSimpleName();

	@Test
	public void login() throws Exception
	{
		String testData = testUtil.getTestData( TEST_NAME, "login.json" );
		this.mockMvc.perform( post( "/api/login" )
				.contentType( MediaType.APPLICATION_JSON )
				.content( testData ) 
			)
			.andDo( print() )
			.andExpect( status().isOk() )
			.andDo( document( "login",
						requestFields( 
								fieldWithPath( "username" ).description( "Email cím" ),
								fieldWithPath( "password" ).description( "Jelszó" ),
								fieldWithPath( "company" ).description( "Cég azonosító, admin esetében nem kötelező" )
									.optional().type( JsonFieldType.STRING )
						), responseFields(
							fieldWithPath( "token" ).description( "Json Web Token" )	
						)
					) 
				);
	}
}
