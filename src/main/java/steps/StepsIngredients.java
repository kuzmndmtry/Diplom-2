package steps;

import client.RestClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class StepsIngredients extends RestClient {
    private static final String ORDER_INGREDIENTS = "/api/ingredients";
    @Step("Send POST request to /api/orders")
    public Response sendGetRequestIngredients() {
        Response response =
                getDefaultRequestSpecification()
                        .get(ORDER_INGREDIENTS);
        return response;
    }
}
