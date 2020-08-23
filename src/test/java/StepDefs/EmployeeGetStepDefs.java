package StepDefs;

import Model.RequestResponseSpecData;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.hamcrest.Matchers.*;

/*
 * Below Cucumber Step Defenitions for all Get Api's within the test. Eg: Login, Logout.
 * */


public class EmployeeGetStepDefs extends GenericActions {

    @Given("^I have the 'login' stub service up and running with the URL \"([^\"]*)\" and below credentials$")
    public void iHaveTheLoginStubServiceUpAndRunning(String path, List<String> loginCreds) {
        requestResponseSpecData.setPath(path);
        requestResponseSpecData.setLoginCreds(loginCreds);
        restStubsInitiateLibrary.getEmployeeLoginStubInitiate(path, loginCreds);
    }

    @When("^I do a GET API request to login$")
    public void iDoAGetApiRequestToLogin() throws URISyntaxException {

        Response response = RestAssured.given()
                .when()
                .auth()
                .preemptive()
                .basic(requestResponseSpecData.getLoginCreds().get(0), requestResponseSpecData.getLoginCreds().get(1))
                .get(new URI(baseStubUrl + requestResponseSpecData.getPath()))
                .then()
                .extract()
                .response();

        requestResponseSpecData.setResponse(response);
    }

    @Then("^I verify the success message for login and response status code as OK")
    public void iVerifyTheSuccessMessageAndResponseStatusCodeAsOkForLogin() {

        requestResponseSpecData.getResponse()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(containsString("successful operation"));
    }


    @Given("^I have the 'logout' stub service up and running with the URL \"([^\"]*)\"$")
    public void iHaveTheLogoutStubServiceUpAndRunning(String path) {
        requestResponseSpecData.setPath(path);
        restStubsInitiateLibrary.getEmployeeLogoutStubInitiate(path);
    }

    @When("^I do a GET API request to logout")
    public void iDoAGetApiRequestToLogout() throws URISyntaxException {

        Response response = RestAssured.given()
                .when()
                .get(new URI(baseStubUrl + requestResponseSpecData.getPath()))
                .then()
                .extract()
                .response();

        requestResponseSpecData.setResponse(response);
    }

    @Then("^I verify the success message for logout and response status code as OK")
    public void iVerifyTheSuccessMessageAndResponseStatusCodeAsOkForLogout() {

        requestResponseSpecData.getResponse()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(containsString("successful operation"));
    }

    @Given("^I have the 'getEmployee' stub service up and running with the URL \"([^\"]*)\" to return details of employee \"([^\"]*)\"$")
    public void iHaveThegetEmployeetStubServiceUpAndRunning(String path, String employeeName) {
        requestResponseSpecData.setPath(path);
        requestResponseSpecData.setEmployeename(employeeName);
        restStubsInitiateLibrary.getEmployeeEmployeeNameStubInitiate(path, employeeName);
    }

    @When("^I do a GET API request to get employee details")
    public void iDoAGetApiRequestToGetEmployeeDetails() throws URISyntaxException {
        RestAssured.defaultParser = Parser.JSON;
        Response response = RestAssured.given()
                .when()
                .baseUri(baseStubUrl)
                .pathParam("employeename", requestResponseSpecData.getEmployeename())
                .basePath(requestResponseSpecData.getPath() + "/{employeename}")
                .get()
                .then()
                .extract()
                .response();

        requestResponseSpecData.setResponse(response);
    }

    @Then("^I verify the response of 'getEmployee' api service$")
    public void iVerifyTheResponseOfGetEmployeeApiService() {

        requestResponseSpecData.getResponse()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("empname", is(notNullValue()))
                .body("empid", is(notNullValue()))
                .body("gender", is(notNullValue()))
                .body("designation", is(notNullValue()))
                .body("office", is(notNullValue()))
                .body("email", is(notNullValue()));
    }
}
