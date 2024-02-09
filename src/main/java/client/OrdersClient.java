package client;

import dto.orders.OrderRequestBody;
import io.restassured.response.Response;
import steps.StepsOrders;

import java.util.List;

public class OrdersClient {
    private  final StepsOrders stepsOrders;

    public OrdersClient(StepsOrders stepsOrders) {
        this.stepsOrders = stepsOrders;
    }

    public Response create(List<String> ingredients) {
        OrderRequestBody orderRequestBody = new OrderRequestBody();
        orderRequestBody.setIngredients(ingredients);
        return stepsOrders.sendPostRequestOrderCreate(orderRequestBody);
    }
    public Response create(String authorizationToken, List<String> ingredients) {
        OrderRequestBody orderRequestBody = new OrderRequestBody();
        orderRequestBody.setIngredients(ingredients);
        return stepsOrders.sendPostRequestOrderCreate(authorizationToken,orderRequestBody);
    }
    public Response create(String authorizationToken) {
        OrderRequestBody orderRequestBody = new OrderRequestBody();
        orderRequestBody.setIngredients(null);
        return stepsOrders.sendPostRequestOrderCreate(authorizationToken,orderRequestBody);
    }
    public Response get(String authorizationToken){
        return stepsOrders.sendGetRequestOrder(authorizationToken);
    }
    public Response get(){
        return stepsOrders.sendGetRequestOrder();
    }
}
