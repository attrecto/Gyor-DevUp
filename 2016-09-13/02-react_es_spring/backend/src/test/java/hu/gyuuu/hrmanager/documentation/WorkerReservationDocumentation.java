package hu.gyuuu.hrmanager.documentation;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

import hu.gyuuu.hrmanager.security.enums.Authority;
import hu.gyuuu.hrmanager.test.DocumentationTest;
import hu.gyuuu.hrmanager.test.TestUser;

public class WorkerReservationDocumentation extends DocumentationTest {

    private static final String TEST_NAME = WorkerReservationDocumentation.class.getSimpleName();
    
    @Test
    public void decideWorkerRegistration() throws Exception{
        this.mockMvc.perform( post( "/api/workerReservations/decide" )
                .contentType( MediaType.APPLICATION_JSON )
                .header( HEADER_AUTH, testUtil.getAuthHeader( TestUser.ADMIN, Authority.DECIDE_OWN_WORKER_RESERVATION ) )
                .content( testUtil.getTestData( TEST_NAME, "decide.json" ) )
                )
        .andDo( print() )
        .andExpect( status().isNoContent() )
        .andDo( document( "decideWorkerRegistration",
                requestFields( 
                        fieldWithPath( "reservationId" ).description( "Az érintett foglalás id-ja" ),
                        fieldWithPath( "decision" ).description( "APPROVE -> elfogadás,  REJECT -> elutasítás" )    
                        )
                ) 
                );
    }
}
