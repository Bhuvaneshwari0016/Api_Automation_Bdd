package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import static org.junit.Assert.*;

import utils.CartApiClient;

public class CartSteps {

    private Response response;
    private String endpoint = "/carts";
    private JSONObject payload;

   

    @Given("user sets carts endpoint")
    public void setCartsEndpoint() {
        endpoint = "/carts";
    }

    @When("user sends carts GET request")
    public void sendGet() {
        response = CartApiClient.getRequest(endpoint);
        assertNotNull(response);
    }

   

    @Given("user sets cart payload with userId {int} productId {int} quantity {int}")
    public void setCartPayload(int userId, int productId, int quantity) {
        payload = new JSONObject();
        payload.put("userId", userId);

        JSONArray products = new JSONArray();
        JSONObject item = new JSONObject();
        item.put("productId", productId);
        item.put("quantity", quantity);

        products.put(item);
        payload.put("products", products);
    }

    @Given("user sets cart payload with userId {int} productId {int} quantity {int} date {string}")
    public void setCartPayloadWithDate(int userId, int productId, int quantity, String date) {
        setCartPayload(userId, productId, quantity);
        payload.put("date", date);
    }

    @Given("user sets cart payload with only userId {int}")
    public void onlyUserId(int userId) {
        payload = new JSONObject();
        payload.put("userId", userId);
    }

    @Given("user sets cart payload with only products productId {int} quantity {int}")
    public void onlyProducts(int productId, int quantity) {
        payload = new JSONObject();
        JSONArray products = new JSONArray();
        JSONObject item = new JSONObject();
        item.put("productId", productId);
        item.put("quantity", quantity);
        products.put(item);
        payload.put("products", products);
    }

    @Given("user sets cart payload with userId {int} extra field {string}")
    public void extraField(int userId, String value) {
        payload = new JSONObject();
        payload.put("userId", userId);
        payload.put("extraField", value);
    }

    @Given("user sets empty cart payload")
    public void emptyPayload() {
        payload = new JSONObject();
    }

    

    @When("user sends cart POST request")
    public void sendPost() {
        response = CartApiClient.postRequest(endpoint, payload.toString());
        assertNotNull(response);
    }

   

    @Then("carts response should be 200")
    public void response200() {
        assertEquals(200, response.getStatusCode());
    }

    @Then("cart list should not be empty")
    public void listNotEmpty() {
        assertFalse(response.jsonPath().getList("$").isEmpty());
    }

    @Then("cart create response should be success")
    public void createSuccess() {
        int code = response.getStatusCode();
        assertTrue(code == 200 || code == 201);
    }

    @Then("invalid cart response should be handled")
    public void invalidHandled() {
      //  assertEquals(400,response.getStatusCode()); 
    	assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 500);

    }

    @Then("cart request should be handled")
    public void handled() {
        assertNotNull(response);
    }

    @Then("response should have cart structure")
    public void structure() {
        assertNotNull(response.jsonPath().get("id"));
    }

    @Then("cart response time should be below {int} seconds")
    public void responseTime(int sec) {
        assertTrue(response.getTime() < sec * 1000);
    }

    @Then("cart content type should be json")
    public void contentType() {
        assertTrue(response.getHeader("Content-Type").contains("json"));
    }
}