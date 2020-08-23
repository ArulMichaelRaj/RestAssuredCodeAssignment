package StepDefs;

import com.github.tomakehurst.wiremock.WireMockServer;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;

public class Hooks {
    public static WireMockServer wireMockServer;

    /*
     * Starting wiremock server and loading all stubs
     */

    @Before
    public void startServerAndLoadAllStubs() {
        if (wireMockServer == null) {
            wireMockServer = new WireMockServer(8888);
            configureFor("localhost", 8888);
            wireMockServer.start();
            new RestStubsInitiateLibrary().setWireMockServer(wireMockServer);
        }
    }
}
