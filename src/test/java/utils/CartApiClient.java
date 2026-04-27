package utils;
 
import io.restassured.response.Response;
 
import static io.restassured.RestAssured.*;
 
public class CartApiClient {
 
public static Response getRequest(String endpoint){
 
return given()
.baseUri("https://fakestoreapi.com")
.when()
.get(endpoint)
.then()
.extract().response();
 
}
 
public static Response postRequest(String endpoint,String body){
 
return given()
.baseUri("https://fakestoreapi.com")
.header("Content-Type","application/json")
.body(body)
.when()
.post(endpoint)
.then()
.extract().response();
 
}
 
}