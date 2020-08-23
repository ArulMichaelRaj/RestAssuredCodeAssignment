package StepDefs;

import com.github.tomakehurst.wiremock.WireMockServer;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;

public class Hooks {
    public static WireMockServer wireMockServer;

    /*
     * Starting wiremock server and loading all stubs
     */

    @Before
    public void startServerAndLoadAllStubs() throws IOException {
        new GenericActions().loadProperties();
        int wiremockport = Integer.parseInt(GenericActions.wiremockport);
        if (wireMockServer == null) {
            wireMockServer = new WireMockServer(wiremockport);
            configureFor("localhost", wiremockport);
            wireMockServer.start();
            new RestStubsInitiateLibrary().setWireMockServer(wireMockServer);
        }
    }
}
