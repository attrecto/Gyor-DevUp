package hu.gyuuu.hrmanager.documentation;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

import hu.gyuuu.hrmanager.test.DocumentationTest;

public class SignupDocumentation extends DocumentationTest
{
	private static final String TEST_NAME = SignupDocumentation.class.getSimpleName();

	@Test
	public void signup() throws Exception
	{
		String testData = testUtil.getTestData( TEST_NAME, "signup.json" );
		this.mockMvc.perform( post( "/api/signup" )
				.contentType( MediaType.APPLICATION_JSON )
				.content( testData ) 
			)
			.andDo( print() )
			.andExpect( status().isNoContent() )
			.andDo( document( "signup",
						requestFields( 
								fieldWithPath( "user.email" ).description( "Felhasználó email címe" ),
								fieldWithPath( "user.name" ).description( "Felhasználó neve" ),
								fieldWithPath( "user.password" ).description( "Felhasználó jelszava" ),
								fieldWithPath( "company.identifier" ).description( "Cég egyedi azonosítója" ), 
								fieldWithPath( "company.name" ).description( "Cég neve" ), 
								fieldWithPath( "company.contact.name" ).description( "Cég kontakt személy neve" ), 
								fieldWithPath( "company.contact.email" ).description( "Cég kontakt személy email címe" ), 
								fieldWithPath( "company.contact.phone" ).description( "Cég kontakt személy telefon száma" ).optional()
						) ) );
	}
}
