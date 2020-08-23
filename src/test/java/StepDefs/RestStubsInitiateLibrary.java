package StepDefs;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.http.HttpStatus;
import java.util.List;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class RestStubsInitiateLibrary {
    private static WireMockServer wireMockServer;

    public void setWireMockServer(WireMockServer wireMockServer) {
        this.wireMockServer = wireMockServer;
    }


    public void postCreateEmployeeStubInitiate(String path) {
        WireMock.stubFor(post((path))
                .withBasicAuth("mike@test.com", "test#123")
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_CREATED)
                        .withHeader("Content-Type", "applicaton/json")
                        .withBody("{\"result\":\"successful operation\"}")));
    }

    public void postCreateMultipleEmployeeStubInitiate(String path) {
        WireMock.stubFor(post((path))
                .withBasicAuth("mike@test.com", "test#123")
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_CREATED)
                        .withHeader("Content-Type", "applicaton/json")
                        .withBody("{\"result\":\"successful operation\"}")));
    }

    public void getEmployeeLoginStubInitiate(String path, List<String> loginCreds) {
        WireMock.stubFor(get((path))
                .withBasicAuth(loginCreds.get(0), loginCreds.get(1))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "applicaton/json")
                        .withBody("{\"result\":\"successful operation\"}")));
    }

    public void getEmployeeLogoutStubInitiate(String path) {
        WireMock.stubFor(get((path))
                .willReturn(aResponse()
                        .withBodyFile("")
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "applicaton/json")
                        .withBody("{\"result\":\"successful operation\"}")));
    }

    public void getEmployeeEmployeeNameStubInitiate(String path, String employeename) {
        WireMock.stubFor(get(path + "/" + employeename)
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "applicaton/json")
                        .withBodyFile("getEmployeeDetails.json")));
    }

    public void putEmployeeEmployeeNameStubInitiate(String path, String employeename) {
        WireMock.stubFor(put(path + "/" + employeename)
                .withBasicAuth("mike@test.com", "test#123")
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_ACCEPTED)
                        .withHeader("Content-Type", "applicaton/json")
                        .withBody("{\"result\":\"Record Updated\"}")));
    }

    public void deleteEmployeeEmployeeNameStubInitiate(String path, String employeename) {
        WireMock.stubFor(delete(path + "/" + employeename)
                .withBasicAuth("mike@test.com", "test#123")
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "applicaton/json")
                        .withBody("{\"result\":\"Record Updated\"}")));
    }

    public void errorStubInitiate() {
        WireMock.stubFor(any(anyUrl())
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_NOT_FOUND)
                        .withBody("{\"result\":\"Failed Operation\"}")));

    }
}