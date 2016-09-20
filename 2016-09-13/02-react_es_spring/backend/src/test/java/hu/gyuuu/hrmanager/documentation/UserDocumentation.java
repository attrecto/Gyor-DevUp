package hu.gyuuu.hrmanager.documentation;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import hu.gyuuu.hrmanager.security.enums.Authority;
import hu.gyuuu.hrmanager.test.DocumentationTest;
import hu.gyuuu.hrmanager.test.TestUser;

public class UserDocumentation extends DocumentationTest
{
	
	@Test
	public void selfProfileData() throws Exception{
		this.mockMvc.perform( get( "/api/users/me" )
				.contentType( MediaType.APPLICATION_JSON )
				.header( HEADER_AUTH, testUtil.getAuthHeader( TestUser.ADMIN, Authority.VIEW_SELF ) )
				)
			.andDo( print() )
			.andExpect( status().isOk() )
			.andDo( document( "selfProfile",
						responseFields(
							fieldWithPath( "user.id" ).description( "Felhasználó id" ),
							fieldWithPath( "user.name" ).description( "Felhasználó neve" ),	
							fieldWithPath( "user.email" ).description( "Felhasználó email címe" ),	
							fieldWithPath( "companies" ).description( "Kapcsolódó cégek adatai" ).type(JsonFieldType.ARRAY).optional(),	
							fieldWithPath( "companies[].id" ).description( "Kapcsolódó cég id" ).type(JsonFieldType.NUMBER).optional(),	
							fieldWithPath( "companies[].name" ).description( "Kapcsolódó cég neve" ).type(JsonFieldType.STRING).optional(),	
							fieldWithPath( "companies[].identifier" ).description( "Kapcsolódó cég azonosítója" ).type(JsonFieldType.STRING).optional(),	
							fieldWithPath( "companies[].contact.name" ).description( "Kapcsolódó cég kapcsolattartó neve" ).type(JsonFieldType.STRING).optional(),	
							fieldWithPath( "companies[].contact.phone" ).description( "Kapcsolódó cég kapcsolattartó telefon száma" ).type(JsonFieldType.STRING).optional(),	
							fieldWithPath( "companies[].contact.email" ).description( "Kapcsolódó cég kapcsolattartó email címe" ).type(JsonFieldType.STRING).optional()	
						)
					) 
				);
	}
	
	@Test
	public void profileData() throws Exception{
	    this.mockMvc.perform( get( "/api/users/{userId}", TestUser.APPROVED_USER_1.getUserId() )
	            .contentType( MediaType.APPLICATION_JSON )
	            .header( HEADER_AUTH, testUtil.getAuthHeader( TestUser.ADMIN, Authority.VIEW_USERS ) )
	            )
	    .andDo( print() )
	    .andExpect( status().isOk() )
	    .andDo( document( "profile",
	            pathParameters(
	                    parameterWithName("userId").description("A lekérni kívánt felhasználó id-ja")
	                    ),
	            responseFields(
	                    fieldWithPath( "user.id" ).description( "Felhasználó id" ),
	                    fieldWithPath( "user.name" ).description( "Felhasználó neve" ),	
	                    fieldWithPath( "user.email" ).description( "Felhasználó email címe" ),	
	                    fieldWithPath( "companies" ).description( "Kapcsolódó cégek adatai" ).type(JsonFieldType.ARRAY).optional(),    
	                    fieldWithPath( "companies[].id" ).description( "Kapcsolódó cég id" ).type(JsonFieldType.NUMBER).optional(),	
	                    fieldWithPath( "companies[].name" ).description( "Kapcsolódó cég neve" ).type(JsonFieldType.STRING).optional(),	
	                    fieldWithPath( "companies[].identifier" ).description( "Kapcsolódó cég azonosítója" ).type(JsonFieldType.STRING).optional(),	
	                    fieldWithPath( "companies[].contact.name" ).description( "Kapcsolódó cég kapcsolattartó neve" ).type(JsonFieldType.STRING).optional(),	
	                    fieldWithPath( "companies[].contact.phone" ).description( "Kapcsolódó cég kapcsolattartó telefon száma" ).type(JsonFieldType.STRING).optional(),	
	                    fieldWithPath( "companies[].contact.email" ).description( "Kapcsolódó cég kapcsolattartó email címe" ).type(JsonFieldType.STRING).optional()	
	                    )
	            ) 
	            );
	}
	
}
