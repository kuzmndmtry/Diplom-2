package steps;

import client.RestClient;
import dto.orders.OrderRequestBody;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class StepsOrders extends RestClient {
    private static final String ORDERS = "/api/orders";
    private static final String ORDERS_ALL = "/api/orders/all";
    @Step("Send POST request to /api/orders")
    public Response sendPostRequestOrderCreate(OrderRequestBody orderRequestBody) {
        Response response =
                getDefaultRequestSpecification()
                        .body(orderRequestBody)
                        .post(ORDERS);
        return response;
    }
    @Step("Send POST request to /api/orders")
    public Response sendPostRequestOrderCreate(String authorizationToken, OrderRequestBody orderRequestBody) {
        Response response =
                getDefaultRequestSpecification()
                        .header("Authorization", authorizationToken)
                        .body(orderRequestBody)
                        .post(ORDERS);
        return response;
    }
    @Step("Send GET request to /api/orders")
    public Response sendGetRequestOrder(String authorizationToken) {
        Response response =
                getDefaultRequestSpecification()
                        .header("Authorization", authorizationToken)
                        .get(ORDERS);
        return response;
    }
    @Step("Send GET request to /api/orders/all")
    public Response sendGetRequestOrder() {
        Response response =
                getDefaultRequestSpecification()
                        .get(ORDERS_ALL);
        return response;
    }
}
