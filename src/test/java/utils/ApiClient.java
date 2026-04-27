package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ApiClient {

    static {
       
        RestAssured.useRelaxedHTTPSValidation();
    }

    public static Response postRequest(String endpoint, String body) {
        return given()
                .baseUri("https://fakestoreapi.com")
                .contentType("application/json")
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }
}