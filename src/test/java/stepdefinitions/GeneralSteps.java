package stepdefinitions;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.junit.Assert.*;

public class GeneralSteps {

    Response response;

    @Given("base url is configured")
    public void setBaseUrl() {
        RestAssured.baseURI = "https://fakestoreapi.com";
    }

    @When("user sends {string} request to {string}")
    public void sendRequest(String method, String endpoint) {

        switch (method.toUpperCase()) {
            case "GET":
                response = RestAssured.given().get(endpoint);
                break;
            case "POST":
                response = RestAssured.given().post(endpoint);
                break;
            case "PUT":
                response = RestAssured.given().put(endpoint);
                break;
            case "DELETE":
                response = RestAssured.given().delete(endpoint);
                break;
            default:
                throw new IllegalArgumentException("Invalid HTTP method");
        }
    }

    @When("user sends GET request to {string}")
    public void sendGetRequest(String endpoint) {
        response = RestAssured.given().get(endpoint);
    }

    @Then("response status code should be {int}")
    public void verifyStatusCode(int expectedCode) {
        assertEquals(expectedCode, response.getStatusCode());
    }

    @Then("API should be available")
    public void verifyApiAvailability() {
        assertTrue(response.getStatusCode() < 500);
    }
}