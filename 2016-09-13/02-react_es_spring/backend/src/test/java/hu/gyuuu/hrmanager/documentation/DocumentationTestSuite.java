package hu.gyuuu.hrmanager.documentation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   AuthenticationDocumentation.class,
   SignupDocumentation.class,
   PendingRegistrationDocumentation.class,
   UserDocumentation.class,
   CompanyDocumentation.class,
   WorkerReservationDocumentation.class
})
public class DocumentationTestSuite {

}
