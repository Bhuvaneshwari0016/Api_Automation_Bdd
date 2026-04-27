package utils;
 
import io.restassured.response.Response;
 
import static io.restassured.RestAssured.*;
 
public class ProductApiClient {
 
public static Response getRequest(String endpoint){
 
return given()
        .baseUri("https://fakestoreapi.com")
        .when()
        .get(endpoint)
        .then()
        .extract().response();
 
}
 
}