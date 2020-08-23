package StepDefs;

import Model.RequestResponseSpecData;
import com.assingment.api.operations.employee.SpringIntegrationTest;
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


public class StepDefs extends SpringIntegrationTest {

    private RequestResponseSpecData requestResponseSpecData = new RequestResponseSpecData();
    private RestStubsInitiateLibrary restStubsInitiateLibrary = new RestStubsInitiateLibrary();
    private static String baseStubUrl = "http://localhost:8888";


    @Given("^I have the 'create employee' stub service up and running with the URL \"([^\"]*)\"$")
    public void iHaveTheCreateEmployeeStubServiceUpAndRunning(String path) {
        requestResponseSpecData.setPath(path);
        restStubsInitiateLibrary.postCreateEmployeeStubInitiate(path);
    }

    @When("^I do a POST API request to create an employee$")
    public void iDoAPostApiRequestToCreateEmployeeURL() throws URISyntaxException {

        Response response = RestAssured.given()
                .auth().preemptive().basic("mike@test.com", "test#123").when()

                .post(new URI(baseStubUrl + requestResponseSpecData.getPath()))
                .then()
                .extract()
                .response();

        requestResponseSpecData.setResponse(response);
    }

    @Then("^I verify the success message and response status code as OK")
    public void iVerifyTheSuccessMessageAndResponseStatusCodeAsOk() {

        requestResponseSpecData.getResponse()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(containsString("successful operation"));
    }

    @Then("^I verify the success message and response status code as CREATED")
    public void iVerifyTheSuccessMessageAndResponseStatusCodeAsCreated() {

        requestResponseSpecData.getResponse()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body(containsString("successful operation"));
    }

    @Given("^I have the 'create multiple employee' stub service up and running with the URL \"([^\"]*)\"$")
    public void iHaveTheCreateMultipleEmployeeStubServiceUpAndRunning(String path) {
        requestResponseSpecData.setPath(path);
        restStubsInitiateLibrary.postCreateMultipleEmployeeStubInitiate(path);
    }

    @When("^I do a POST API request to create an employee to create multiple employees$")
    public void iDoAPostApiRequestToCreateMutipleEmployee(List<String> employees) throws URISyntaxException {

        Response response = RestAssured.given()
                .auth().preemptive().basic("mike@test.com", "test#123").formParam("empName", employees).when()
                .post(new URI(baseStubUrl + requestResponseSpecData.getPath()))
                .then()
                .extract()
                .response();

        requestResponseSpecData.setResponse(response);
    }

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

    @Given("^I have the 'updateEmployee' stub service up and running with the URL \"([^\"]*)\" to update details of employee \"([^\"]*)\"$")
    public void iHaveTheupdateEmployeetStubServiceUpAndRunning(String path, String employeeName) {
        requestResponseSpecData.setPath(path);
        requestResponseSpecData.setEmployeename(employeeName);
        restStubsInitiateLibrary.putEmployeeEmployeeNameStubInitiate(path, employeeName);
    }

    @When("^I do a PUT API request to update employeename")
    public void iDoAPutApiRequestToUpdateEmplopyeename() throws URISyntaxException {
        RestAssured.defaultParser = Parser.JSON;

        RestAssured.defaultParser = Parser.JSON;
        Response response = RestAssured.given()
                .when().auth().preemptive().basic("mike@test.com", "test#123")
                .baseUri(baseStubUrl)
                .pathParam("employeename", requestResponseSpecData.getEmployeename())
                .basePath(requestResponseSpecData.getPath() + "/{employeename}")
                .put()
                .then()
                .extract()
                .response();

        requestResponseSpecData.setResponse(response);
    }

    @Then("^I verify the response of 'updateEmployee' api service$")
    public void iVerifyTheResponseOfUpdateEmployeeApiService() {

        requestResponseSpecData.getResponse()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_ACCEPTED)
                .body(containsString("Record Updated"));
    }

    @Given("^I have the 'deleteEmployee' stub service up and running with the URL \"([^\"]*)\" to delete details of employee \"([^\"]*)\"$")
    public void iHaveThedeleteEmployeetStubServiceUpAndRunning(String path, String employeeName) {
        requestResponseSpecData.setPath(path);
        requestResponseSpecData.setEmployeename(employeeName);
        restStubsInitiateLibrary.deleteEmployeeEmployeeNameStubInitiate(path, employeeName);
    }

    @When("^I do a DELETE API request to delete employeename")
    public void iDoADeleteApiRequestToDeleteEmplopyeename() throws URISyntaxException {

        Response response = RestAssured.given()
                .when().auth().preemptive().basic("mike@test.com", "test#123")
                .baseUri(baseStubUrl)
                .pathParam("employeename", requestResponseSpecData.getEmployeename())
                .basePath(requestResponseSpecData.getPath() + "/{employeename}")
                .delete()
                .then()
                .extract()
                .response();

        requestResponseSpecData.setResponse(response);
    }

    @Then("^I verify the response of 'deleteEmployee' api service$")
    public void iVerifyTheResponseOfDeleteEmployeeApiService() {

        requestResponseSpecData.getResponse()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(containsString("Record Updated"));
    }

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

