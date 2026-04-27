package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.json.JSONObject;

import static org.junit.Assert.*;
import utils.ApiClient;

public class AuthSteps {

    private Response response;
    private JSONObject payload;

    @Given("user sets login payload with username {string} and password {string}")
    public void set_payload(String username, String password) {
        payload = new JSONObject();
        payload.put("username", username);
        payload.put("password", password);
    }

    @Given("user sets empty login payload")
    public void empty_payload() {
        payload = new JSONObject();
    }

    @When("user sends POST request to {string}")
    public void send_request(String endpoint) {
        response = ApiClient.postRequest(endpoint, payload.toString());
        assertNotNull("Response is null", response);
    }

    
    @Then("response status should be {int}")
    public void validate_status(int status) {
        assertEquals(status, response.getStatusCode());
    }

    @Then("response status should not be 200")
    public void not_200() {
        assertNotEquals(200, response.getStatusCode());
    }

    
    @Then("response should contain token")
    public void validate_token() {
        assertFalse("Response body is empty", response.getBody().asString().isEmpty());

        String token = response.jsonPath().getString("token");
        assertNotNull("Token missing", token);
        assertTrue("Token is not a string", token instanceof String);
    }

    
    @Then("response should be error")
    public void error_response() {
        assertTrue(response.getStatusCode() == 400 || response.getStatusCode() == 401);
        assertFalse("Error response body is empty", response.getBody().asString().isEmpty());
    }

    @Then("request should fail")
    public void request_fail() {
        assertTrue(response.getStatusCode() >= 400);
    }

    
    @Then("response should be handled properly")
    public void handled_properly() {
        assertNotNull(response.getBody());
        assertFalse(response.getBody().asString().isEmpty());
    }

    @Then("response should be handled safely")
    public void handled_safely() {
        String body = response.getBody().asString();
        assertNotNull(body);
        assertFalse(body.isEmpty());
        assertFalse("Response exposes exception", body.toLowerCase().contains("exception"));
    }

    
    @Then("response time should be less than {int} seconds")
    public void response_time(int seconds) {
        long time = response.getTime();
        assertTrue("Response too slow: " + time, time < seconds * 1000);
    }
}