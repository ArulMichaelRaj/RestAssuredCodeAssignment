package StepDefs;

import Model.RequestResponseSpecData;
import com.assingment.api.operations.employee.SpringIntegrationTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GenericActions extends SpringIntegrationTest {
    protected RequestResponseSpecData requestResponseSpecData = new RequestResponseSpecData();
    protected RestStubsInitiateLibrary restStubsInitiateLibrary = new RestStubsInitiateLibrary();
    protected static String baseStubUrl;
    protected static String wiremockbasehost;
    protected static String wiremockport;

    public void loadProperties() throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/test/resources/TestConfiguration.properties"));
         wiremockbasehost = prop.getProperty("wiremockbasehost");
         wiremockport = (prop.getProperty("wiremockport"));
        baseStubUrl = wiremockbasehost + wiremockport;

    }
}
