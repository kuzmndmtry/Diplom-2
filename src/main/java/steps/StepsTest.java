package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class StepsTest {
    @Step("Compare status code to something")
    public void compareResponseStatusCode(Response response, int statusCode) {
        response
                .then()
                .statusCode(statusCode);
    }
    @Step("Compare body to something")
    public void compareResponseBody(Response response, String object, String value){
        response
                .then()
                .body(String.valueOf(object), equalTo(value));
    }
    @Step("Compare body to something")
    public void compareResponseBody(Response response, String object, int value){
        response
                .then()
                .body(String.valueOf(object), equalTo(value));
    }

    @Step("Contains body to something")
    public void containsResponseBody(Response response, String object, String value){
        response
                .then()
                .body(String.valueOf(object), contains(value));
    }
    @Step("Compare body to something")
    public void compareResponseBody(Response response, String object, boolean value) {
        response
                .then()
                .body(object, equalTo(value));
    }
    @Step("Compare body to something")
    public void compareResponseBody(Response response, String object) {
        response
                .then()
                .body(object, notNullValue());
    }

}
