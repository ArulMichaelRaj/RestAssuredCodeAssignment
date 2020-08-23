package StepDefs;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;


import static org.hamcrest.Matchers.*;


/*
 * Below Cucumber Step Defenitions for genric error handling within the test
 * */

public class GenericStepDefs extends GenericActions {


    @Given("^I try to hit an invalid URL \"([^\"]*)\"$")
    public void iHitAnInvalidUrl(String path) {

        restStubsInitiateLibrary.errorStubInitiate();
        Response response = RestAssured.given()
                .when().auth().preemptive().basic("mike@test.com", "test#123")
                .baseUri(baseStubUrl)
                .basePath(path)
                .get()
                .then()
                .extract()
                .response();

        requestResponseSpecData.setResponse(response);
    }

    @Then("^I verify the response of the invalid URL is NOT_FOUND$")
    public void iVerifyTheResponseOfTheInvalidUrl() {

        requestResponseSpecData.getResponse()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body(containsString("Failed Operation"));
    }
}

