package hu.gyuuu.hrmanager.documentation;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

import hu.gyuuu.hrmanager.security.enums.Authority;
import hu.gyuuu.hrmanager.test.DocumentationTest;
import hu.gyuuu.hrmanager.test.TestUser;

public class PendingRegistrationDocumentation extends DocumentationTest
{
	private static final String TEST_NAME = PendingRegistrationDocumentation.class.getSimpleName();
	@Test
	public void pendingRegistrations() throws Exception{
		this.mockMvc.perform( get( "/api/registrations/pending" )
				.contentType( MediaType.APPLICATION_JSON )
				.header( HEADER_AUTH, testUtil.getAuthHeader( TestUser.ADMIN, Authority.APPROVE_USER ) )
				)
			.andDo( print() )
			.andExpect( status().isOk() )
			.andDo( document( "pendingRegistrations",
						responseFields(
							fieldWithPath( "elements[].user.id" ).description( "Felhasználó id" ),
							fieldWithPath( "elements[].user.name" ).description( "Felhasználó neve" ),	
							fieldWithPath( "elements[].user.email" ).description( "Felhasználó email címe" ),	
							fieldWithPath( "elements[].company.id" ).description( "Kapcsolódó cég id" ),	
							fieldWithPath( "elements[].company.name" ).description( "Kapcsolódó cég neve" ),	
							fieldWithPath( "elements[].company.identifier" ).description( "Kapcsolódó cég azonosítója" ),	
							fieldWithPath( "elements[].company.contact.name" ).description( "Kapcsolódó cég kapcsolattartó neve" ),	
							fieldWithPath( "elements[].company.contact.phone" ).description( "Kapcsolódó cég kapcsolattartó telefon száma" ),	
							fieldWithPath( "elements[].company.contact.email" ).description( "Kapcsolódó cég kapcsolattartó email címe" )	
						)
					) 
				);
	}
	
	@Test
	public void decide() throws Exception{
		this.mockMvc.perform( post( "/api/registrations/pending/decide" )
				.contentType( MediaType.APPLICATION_JSON )
				.header( HEADER_AUTH, testUtil.getAuthHeader( TestUser.ADMIN, Authority.APPROVE_USER ) )
				.content( testUtil.getTestData( TEST_NAME, "decide.json" ) )
				)
		.andDo( print() )
		.andExpect( status().isNoContent() )
		.andDo( document( "decide",
				requestFields( 
						fieldWithPath( "userId" ).description( "Az érintett felhasználó id-ja" ),
						fieldWithPath( "decision" ).description( "APPROVE -> elfogadás,  REJECT -> elutasítás" )	
						)
				) 
				);
	}
}
