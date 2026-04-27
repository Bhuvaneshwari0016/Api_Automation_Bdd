package stepdefinitions;
 
import io.cucumber.java.en.*;
import io.restassured.response.Response;
 
import java.util.List;
 
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
 
import utils.ProductApiClient;
 
public class ProductSteps {
 
Response response;
String endpoint;
 
 
@Given("user sets products endpoint")
public void productsEndpoint(){
endpoint="/products";
}
 
@Given("user sets product id {int}")
public void productId(int id){
endpoint="/products/"+id;
}
 
@Given("user sets product path {string}")
public void productPath(String val){
endpoint="/products/"+val;
}
 
@Given("user sets invalid endpoint {string}")
public void invalidEndpoint(String ep){
endpoint=ep;
}
 
@When("user sends products GET request")
public void sendGet(){
 
response=ProductApiClient.getRequest(endpoint);
 
System.out.println(response.getStatusCode());
System.out.println(response.getBody().asString());
 
}
 
 
@Then("products response should be 200")
public void status200(){
assertEquals(200,response.getStatusCode());
}
 
 
@Then("products list should not be empty")
public void listNotEmpty(){
 
List<Object> products=
response.jsonPath().getList("$");
 
assertFalse(products.isEmpty());
}
 
 
@Then("first product should have id title and price")
public void fieldCheck(){
 
assertNotNull(response.jsonPath().get("[0].id"));
assertNotNull(response.jsonPath().get("[0].title"));
assertNotNull(response.jsonPath().get("[0].price"));
}
 
 
@Then("id and price types should be valid")
public void typeCheck(){
 
int id=response.jsonPath().getInt("[0].id");
float price=response.jsonPath().getFloat("[0].price");
 
assertTrue(id>0);
assertTrue(price>0);
 
}
 
 
@Then("invalid product response should be handled")
public void invalidHandled(){
 
String body=response.getBody().asString();
 
assertTrue(
body.contains("{}")
|| body.isEmpty()
|| response.getStatusCode()==404
|| response.getStatusCode()==200
);
 
}
 
 
@Then("products response time should be below {int} seconds")
public void timeCheck(int sec){
 
assertTrue(
response.getTime()<sec*1000
);
 
}
 
 
@Then("all prices should be greater than zero")
public void pricesPositive(){
 
List<Number> prices=
response.jsonPath().getList("price");
 
for(Number p:prices){
assertTrue(p.doubleValue()>0);
}
 
}
 
 
@Then("all titles should not be empty")
public void titlesNotEmpty(){
 
List<String> titles=
response.jsonPath().getList("title");
 
for(String t:titles){
assertFalse(t.trim().isEmpty());
}
 
}
 
 
@Then("products response should be 404")
public void status404(){
 
assertEquals(404,response.getStatusCode());
 
}
 
 

 
@When("user sends POST request to products endpoint")
public void unsupportedMethod(){
 
response=
given()
.baseUri("https://fakestoreapi.com")
.post("/products");
 
}
 
 
@Then("unsupported method should be handled")
public void methodHandled(){
 
int code=response.getStatusCode();
 

 
assertTrue("Status Code:"+code,code==201||code==200||code==405||code==415);
 
}
 
 

 
@Then("products response content type should be json")
public void contentTypeCheck(){
 
String type=
response.getHeader("Content-Type");
 
assertTrue(type.contains("json"));
 
}
 
 

 
@Then("large response should be handled")
public void largeResponse(){
 
List<Object> products=
response.jsonPath().getList("$");
 
assertTrue(products.size()>0);
 
assertNotNull(response);
 
}
 
}
 