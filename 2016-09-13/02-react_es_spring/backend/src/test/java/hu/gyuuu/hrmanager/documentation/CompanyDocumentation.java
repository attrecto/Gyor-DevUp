package hu.gyuuu.hrmanager.documentation;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import hu.gyuuu.hrmanager.security.enums.Authority;
import hu.gyuuu.hrmanager.test.DocumentationTest;
import hu.gyuuu.hrmanager.test.TestUser;

public class CompanyDocumentation extends DocumentationTest
{
    
    private static final String TEST_NAME = CompanyDocumentation.class.getSimpleName();
	
    @Test
    public void listCompanies() throws Exception{
        this.mockMvc.perform( get( "/api/companies" )
                .contentType( MediaType.APPLICATION_JSON )
                .header( HEADER_AUTH, testUtil.getAuthHeader( TestUser.APPROVED_USER_1, Authority.VIEW_COMPANIES ) )
                )
        .andDo( print() )
        .andExpect( status().isOk() )
        .andDo( document( "listCompanies",
                responseFields(
                        fieldWithPath( "elements[].id" ).description( "Cég id-ja" ), 
                        fieldWithPath( "elements[].name" ).description( "Cég neve" ), 
                        fieldWithPath( "elements[].identifier" ).description( "Cég egyedi azonosítója" ), 
                        fieldWithPath( "elements[].contact.name" ).description( "Cég kontakt személy neve" ), 
                        fieldWithPath( "elements[].contact.email" ).description( "Cég kontakt személy email címe" ), 
                        fieldWithPath( "elements[].contact.phone" ).description( "Cég kontakt személy telefon száma" ).optional()
                        )
                ) 
                );
    }
    
	@Test
	public void listWorkers() throws Exception{
		this.mockMvc.perform( get( "/api/companies/{companyId}/workers", TestUser.APPROVED_USER_1.getCompanyId() )
				.contentType( MediaType.APPLICATION_JSON )
				.header( HEADER_AUTH, testUtil.getAuthHeader( TestUser.APPROVED_USER_1, Authority.VIEW_COMPANIES ) )
				)
			.andDo( print() )
			.andExpect( status().isOk() )
			.andDo( document( "listWorkers",
			            pathParameters(
	                        parameterWithName("companyId").description("A cég id-ja amihez a dolgozók tartoznak")
	                        ),
						responseFields(
							fieldWithPath( "elements[].id" ).description( "Dolgozó id" ),
							fieldWithPath( "elements[].name" ).description( "Dolgozó neve" ),
							fieldWithPath( "elements[].biography" ).description( "Dolgozó életrajza" ),
							fieldWithPath( "elements[].reservations" ).description( "Dolgozó foglalásai" ).type(JsonFieldType.ARRAY).optional(),
							fieldWithPath( "elements[].reservations[].id" ).description( "Dolgozó foglalás id-ja" ),
	                        fieldWithPath( "elements[].reservations[].startDate" ).description( "Dolgozó foglalás kezdete" ),
	                        fieldWithPath( "elements[].reservations[].endDate" ).description( "Dolgozó foglalás vége" ),
	                        fieldWithPath( "elements[].reservations[].status" ).description( "Foglalás státusza APPROVED -> elfogadott, REJECTED -> elutasított, null -> függőben lévő" ),
	                        fieldWithPath( "elements[].reservations[].bookingCompany.id" ).description( "Foglaló cég id-ja" ),
	                        fieldWithPath( "elements[].reservations[].bookingCompany.name" ).description( "Foglaló cég neve" ),
	                        fieldWithPath( "elements[].reservations[].bookingCompany.identifier" ).description( "Foglaló cég azonosítója" ),
	                        fieldWithPath( "elements[].reservations[].bookingCompany.contact.name" ).description( "Foglaló cég kontak személyének neve" ),
	                        fieldWithPath( "elements[].reservations[].bookingCompany.contact.phone" ).description( "Foglaló cég kontak személyének telfonszáma" ),
	                        fieldWithPath( "elements[].reservations[].bookingCompany.contact.email" ).description( "Foglaló cég kontak személyének email címe" )
						)
					) 
				);
	}
	
	@Test
	public void getWorker() throws Exception{
	    this.mockMvc.perform( get( "/api/companies/{companyId}/workers/{workerId}", TestUser.APPROVED_USER_1.getCompanyId(), 7l )
	            .contentType( MediaType.APPLICATION_JSON )
	            .header( HEADER_AUTH, testUtil.getAuthHeader( TestUser.APPROVED_USER_1, Authority.VIEW_WORKERS ) )
	            )
	    .andDo( print() )
	    .andExpect( status().isOk() )
	    .andDo( document( "getWorker",
	            pathParameters(
	                    parameterWithName("companyId").description("A cég id-ja amihez a dolgozó tartozik"),
	                    parameterWithName("workerId").description("A dolgozó id-ja")
	                    ),
	            responseFields(
	                    fieldWithPath( "id" ).description( "Dolgozó id" ),
	                    fieldWithPath( "name" ).description( "Dolgozó neve" ),
	                    fieldWithPath( "biography" ).description( "Dolgozó életrajza" ),
	                    fieldWithPath( "reservations" ).description( "Dolgozó foglalásai" ).type(JsonFieldType.ARRAY).optional(),
	                    fieldWithPath( "reservations[].id" ).description( "Dolgozó foglalás id-ja" ),
	                    fieldWithPath( "reservations[].startDate" ).description( "Dolgozó foglalás kezdete" ),
	                    fieldWithPath( "reservations[].endDate" ).description( "Dolgozó foglalás vége" ),
	                    fieldWithPath( "reservations[].status" ).description( "Foglalás státusza APPROVED -> elfogadott, REJECTED -> elutasított, null -> függőben lévő" ),
	                    fieldWithPath( "reservations[].bookingCompany.id" ).description( "Foglaló cég id-ja" ),
	                    fieldWithPath( "reservations[].bookingCompany.name" ).description( "Foglaló cég neve" ),
	                    fieldWithPath( "reservations[].bookingCompany.identifier" ).description( "Foglaló cég azonosítója" ),
	                    fieldWithPath( "reservations[].bookingCompany.contact.name" ).description( "Foglaló cég kontak személyének neve" ),
	                    fieldWithPath( "reservations[].bookingCompany.contact.phone" ).description( "Foglaló cég kontak személyének telfonszáma" ),
	                    fieldWithPath( "reservations[].bookingCompany.contact.email" ).description( "Foglaló cég kontak személyének email címe" )
	                    )
	            ) 
	            );
	}
	
	@Test
	public void createWorker() throws Exception{
	    this.mockMvc.perform( post( "/api/companies/{companyId}/workers", TestUser.APPROVED_USER_1.getCompanyId() )
	            .contentType( MediaType.APPLICATION_JSON )
	            .header( HEADER_AUTH, testUtil.getAuthHeader( TestUser.APPROVED_USER_1, Authority.MANAGE_OWN_WORKER ) )
	            .content(testUtil.getTestData(TEST_NAME, "createWorker.json"))
	            )
	    .andDo( print() )
	    .andExpect( status().isOk() )
	    .andDo( document( "createWorker",
	            pathParameters(
	                    parameterWithName("companyId").description("A cég id-ja amihez a dolgozót hozzáadjuk")
	                    ),
	            requestFields(
	                    fieldWithPath( "name" ).description( "Dolgozó neve" ),
	                    fieldWithPath( "biography" ).description( "Dolgozó életrajza" )
	                    )
	            ) 
	            );
	}
	
	@Test
	public void editWorker() throws Exception{
	    this.mockMvc.perform( put( "/api/companies/{companyId}/workers/{workerId}", TestUser.APPROVED_USER_1.getCompanyId(), 7l )
	            .contentType( MediaType.APPLICATION_JSON )
	            .header( HEADER_AUTH, testUtil.getAuthHeader( TestUser.ADMIN, Authority.MANAGE_ANY_WORKER ) )
	            .content(testUtil.getTestData(TEST_NAME, "editWorker.json"))
	            )
	    .andDo( print() )
	    .andExpect( status().isNoContent() )
	    .andDo( document( "editWorker",
	            pathParameters(
	                    parameterWithName("companyId").description("A cég id-ja ahol az érintett dolgozó dolgozik"),
	                    parameterWithName("workerId").description("A módosítani kívánt dolgozo id-ja")
	                    ),
	            requestFields(
	                    fieldWithPath( "name" ).description( "Dolgozó neve" ),
	                    fieldWithPath( "biography" ).description( "Dolgozó életrajza" )
	                    )
	            ) 
	            );
	}
	
	@Test
	public void deleteWorker() throws Exception{
	    this.mockMvc.perform( delete( "/api/companies/{companyId}/workers/{workerId}", TestUser.APPROVED_USER_1.getCompanyId(), 9l )
	            .contentType( MediaType.APPLICATION_JSON )
	            .header( HEADER_AUTH, testUtil.getAuthHeader( TestUser.ADMIN, Authority.MANAGE_ANY_WORKER ) )
	            )
	    .andDo( print() )
	    .andExpect( status().isNoContent() )
	    .andDo( document( "deleteWorker",
	            pathParameters(
	                    parameterWithName("companyId").description("A cég id-ja ahol az érintett dolgozó dolgozik"),
	                    parameterWithName("workerId").description("A törölni kívánt dolgozo id-ja")
	                    )
	            ) 
	            );
	}
	
	@Test
	public void reserveWorker() throws Exception{
	    this.mockMvc.perform( post( "/api/companies/{companyId}/workers/{workerId}/reserve", TestUser.APPROVED_USER_1.getCompanyId(), 7l )
	            .contentType( MediaType.APPLICATION_JSON )
	            .header( HEADER_AUTH, testUtil.getAuthHeader( TestUser.APPROVED_USER_1, Authority.RESERVE_WORKER ) )
	            .content(testUtil.getTestData(TEST_NAME, "reserveWorker.json"))
	            )
	    .andDo( print() )
	    .andExpect( status().isNoContent() )
	    .andDo( document( "reserveWorker",
	            pathParameters(
	                    parameterWithName("companyId").description("A cég id-ja ahol az érintett dolgozó dolgozik"),
	                    parameterWithName("workerId").description("A lefoglalni kívánt dolgozo id-ja")
	                    ),
                requestFields(
                        fieldWithPath( "startDate" ).description( "Foglalás kezdete" ),
                        fieldWithPath( "endDate" ).description( "Foglalás vége" )
                        )
	            )
	            );
	}
	
	
}
