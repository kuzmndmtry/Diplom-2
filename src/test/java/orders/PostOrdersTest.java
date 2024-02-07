package orders;

import client.IngredientsClient;
import client.OrdersClient;
import client.UserClient;
import dto.ingredients.IngredientsResponseBody;
import dto.orders.OrderRequestBody;
import dto.user.UserLoginResponseBody;
import dto.user.UserRegisterRequestBody;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.StepsIngredients;
import steps.StepsOrders;
import steps.StepsTest;
import steps.StepsUser;

import java.util.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.apache.http.HttpStatus.*;

public class PostOrdersTest {
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
    @DisplayName("Сheck create new order with valid ingredients and auth")
    @Description("Сhecking the create new order with valid ingredients and auth")
    public void checkCreateNewOrderWithValidIngredientsAndAuth() {
        userClient.create(userRegisterRequestBody);
        UserLoginResponseBody userLoginResponseBody =
                userClient.login(userRegisterRequestBody)
                        .as(UserLoginResponseBody.class);
        IngredientsResponseBody ingredientsResponseBody =
                ingredientsClient.get()
                        .as(IngredientsResponseBody.class);
        Response response =
                orderClient.create(userLoginResponseBody.getAccessToken(), List.of(ingredientsResponseBody
                                .getData()
                                .get(0 + (int) (Math.random() * (ingredientsResponseBody.getData().size())))
                                .get_id(),
                        ingredientsResponseBody
                                .getData()
                                .get(0 + (int) (Math.random() * (ingredientsResponseBody.getData().size())))
                                .get_id()));
        stepsTest.compareResponseStatusCode(response, SC_OK);
        stepsTest.compareResponseBody(response, "success", true);
    }

    @Test
    @DisplayName("Сheck create new order with valid ingredients and without auth")
    @Description("Сhecking the create new order with valid ingredients and without auth")
    public void checkCreateNewOrderWithValidIngredientsAndWithoutAuth() {
        IngredientsResponseBody ingredientsResponseBody =
                ingredientsClient.get()
                        .as(IngredientsResponseBody.class);
        Response response =
                orderClient.create(List.of(ingredientsResponseBody
                                .getData()
                                .get(0 + (int) (Math.random() * (ingredientsResponseBody.getData().size())))
                                .get_id(),
                        ingredientsResponseBody
                                .getData()
                                .get(0 + (int) (Math.random() * (ingredientsResponseBody.getData().size())))
                                .get_id()));
        stepsTest.compareResponseStatusCode(response, SC_UNAUTHORIZED);
        stepsTest.compareResponseBody(response, "success", false);
        stepsTest.compareResponseBody(response, "message", "You should be authorised");
    }

    @Test
    @DisplayName("Check create new order without valid ingredients and with auth")
    @Description("Checking the create new order without valid ingredients and with auth")
    public void checkCreateNewOrderWithoutValidIngredientsAndWithAuth() {
        userClient.create(userRegisterRequestBody);
        UserLoginResponseBody userLoginResponseBody =
                userClient.login(userRegisterRequestBody)
                        .as(UserLoginResponseBody.class);
        IngredientsResponseBody ingredientsResponseBody =
                ingredientsClient.get()
                        .as(IngredientsResponseBody.class);
        Response response =
                orderClient.create(userLoginResponseBody.getAccessToken());
        stepsTest.compareResponseStatusCode(response, SC_BAD_REQUEST);
        stepsTest.compareResponseBody(response, "success", false);
        stepsTest.compareResponseBody(response, "message", "Ingredient ids must be provided");
    }

    @Test
    @DisplayName("Сheck create new order with invalid ingredients and auth")
    @Description("Сhecking the create new order with invalid ingredients and auth")
    public void checkCreateNewOrderWithInvalidIngredientsAndAuth() {
        userClient.create(userRegisterRequestBody);
        UserLoginResponseBody userLoginResponseBody =
                userClient.login(userRegisterRequestBody)
                        .as(UserLoginResponseBody.class);
        IngredientsResponseBody ingredientsResponseBody =
                ingredientsClient.get()
                        .as(IngredientsResponseBody.class);
        Response response =
                orderClient.create(userLoginResponseBody.getAccessToken(),
                        List.of("InvalidIngredient"));
        stepsTest.compareResponseStatusCode(response, SC_INTERNAL_SERVER_ERROR);
    }
}
