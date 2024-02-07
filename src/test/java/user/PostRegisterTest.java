package user;

import client.UserClient;
import dto.user.UserLoginResponseBody;
import dto.user.UserRegisterRequestBody;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.StepsTest;
import steps.StepsUser;

import java.util.Locale;

import static org.apache.http.HttpStatus.*;

public class PostRegisterTest {
    private UserClient userClient;
    private UserRegisterRequestBody userRegisterRequestBody;
    StepsTest stepsTest = new StepsTest();
    @Before
    public void setUp() {
        userClient = new UserClient(new StepsUser());
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
    @DisplayName("Сheck user creation")
    @Description("Сhecking the creation of a new user with a full set of fields")
    public void checkCourierCreationWithAFullSetOfFields() {
        Response response =
                userClient.create(userRegisterRequestBody);
        stepsTest.compareResponseStatusCode(response, SC_OK);
        stepsTest.compareResponseBody(response, "success", true);
        stepsTest.compareResponseBody(response, "user.email", userRegisterRequestBody.getEmail().toLowerCase(Locale.ROOT));
        stepsTest.compareResponseBody(response, "user.name", userRegisterRequestBody.getName());
        stepsTest.compareResponseBody(response, "accessToken");
        stepsTest.compareResponseBody(response, "refreshToken");
    }
    @Test
    @DisplayName("Сheck duplicate user creation")
    @Description("Сhecking the creation of a duplicate user with a full set of fields")
    public void checkDuplicateUserCreation() {
        userClient.create(userRegisterRequestBody);
        Response response =
                userClient.create(userRegisterRequestBody);
        stepsTest.compareResponseStatusCode(response, SC_FORBIDDEN);
        stepsTest.compareResponseBody(response, "success", false);
        stepsTest.compareResponseBody(response, "message", "User with such email already exists");
    }

    @Test
    @DisplayName("Сheck courier creation without email field ")
    public void checkCourierCreationWithoutEmail() {
        userRegisterRequestBody.setEmail(null);
        Response response =
                userClient.create(userRegisterRequestBody);
        stepsTest.compareResponseBody(response, "success", false);
        stepsTest.compareResponseBody(response, "message", "Email, password and name are required fields");
    }
    @Test
    @DisplayName("Сheck courier creation without password field ")
    public void checkCourierCreationWithoutPassword() {
        userRegisterRequestBody.setPassword(null);
        Response response =
                userClient.create(userRegisterRequestBody);
        stepsTest.compareResponseBody(response, "success", false);
        stepsTest.compareResponseBody(response, "message", "Email, password and name are required fields");
    }

    @Test
    @DisplayName("Сheck courier creation without name field ")
    public void checkCourierCreationWithoutName() {
        userRegisterRequestBody.setName(null);
        Response response =
                userClient.create(userRegisterRequestBody);
        stepsTest.compareResponseBody(response, "success", false);
        stepsTest.compareResponseBody(response, "message", "Email, password and name are required fields");
    }
}
