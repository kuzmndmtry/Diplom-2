package orders;

import client.IngredientsClient;
import client.OrdersClient;
import client.UserClient;
import dto.ingredients.IngredientsResponseBody;
import dto.orders.OrderResponseBody;
import dto.user.UserLoginResponseBody;
import dto.user.UserRegisterRequestBody;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.StepsIngredients;
import steps.StepsOrders;
import steps.StepsTest;
import steps.StepsUser;
import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;

public class GetOrderTest {

    private UserClient userClient;
    private OrdersClient orderClient;
    private IngredientsClient ingredientsClient;
    private UserRegisterRequestBody userRegisterRequestBody;

    StepsTest stepsTest = new StepsTest();

    @Before
    public void setUp() {
        userClient = new UserClient(new StepsUser());
        orderClient = new OrdersClient(new StepsOrders());
        ingredientsClient = new IngredientsClient(new StepsIngredients());
        userRegisterRequestBody = new UserRegisterRequestBody();

    }

    @After
    public void deleteTestCourier() {
        UserLoginResponseBody userLoginResponseBody =
                userClient.login(userRegisterRequestBody)
                        .as(UserLoginResponseBody.class);
        if (userLoginResponseBody.getAccessToken() != null) {
            userClient.delete(userLoginResponseBody.getAccessToken());
        }
    }

    @Test
    @DisplayName("Сheck receipt of user orders")
    @Description("Сhecking receipt of user orders")
    public void getCourierOrders() {
        userClient.create(userRegisterRequestBody);
        UserLoginResponseBody userLoginResponseBody =
                userClient.login(userRegisterRequestBody)
                        .as(UserLoginResponseBody.class);
        IngredientsResponseBody ingredientsResponseBody =
                ingredientsClient.get()
                        .as(IngredientsResponseBody.class);
        OrderResponseBody orderResponseBody =
                orderClient.create(userLoginResponseBody.getAccessToken(),
                                List.of(ingredientsResponseBody
                                        .getData()
                                        .get(0 + (int) (Math.random() * (ingredientsResponseBody.getData().size())))
                                        .get_id()))
                        .as(OrderResponseBody.class);
        Response response =
                orderClient.get(userLoginResponseBody.getAccessToken());
        stepsTest.compareResponseStatusCode(response, SC_OK);
        stepsTest.compareResponseBody (response, "success", true);
        stepsTest.compareResponseBody (response, "total", 1);
        stepsTest.containsResponseBody(response, "orders._id", orderResponseBody.getOrder().get_id());
    }
    @Test
    @DisplayName("Сheck receipt of all orders")
    @Description("Сhecking receipt of all orders")
    public void getAllOrders() {
        Response response =
                orderClient.get();
        stepsTest.compareResponseStatusCode(response, SC_OK);
        stepsTest.compareResponseBody (response, "success", true);
        stepsTest.compareResponseBody (response, "total");
    }
}
