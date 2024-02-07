package client;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static config.Config.BASE_URI;
import static io.restassured.RestAssured.given;

public abstract class RestClient {
    protected static RequestSpecification getDefaultRequestSpecification() {
        return given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON);
    }
}