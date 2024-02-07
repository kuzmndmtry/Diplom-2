package user;

import client.UserClient;
import dto.user.UserLoginResponseBody;
import dto.user.UserRegisterRequestBody;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.StepsTest;
import steps.StepsUser;

import java.util.Locale;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

public class PostLoginTest {

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
    @DisplayName("Сheck real user login")
    @Description("Сhecking the real user login")
    public void checkCourierCreationWithAFullSetOfFields() {
        userClient.create(userRegisterRequestBody);
        Response response =
                userClient.login(userRegisterRequestBody);
        stepsTest.compareResponseStatusCode(response, SC_OK);
        stepsTest.compareResponseBody(response, "success", true);
        stepsTest.compareResponseBody(response, "user.email", userRegisterRequestBody.getEmail().toLowerCase(Locale.ROOT));
        stepsTest.compareResponseBody(response, "user.name", userRegisterRequestBody.getName());
        stepsTest.compareResponseBody(response, "accessToken");
        stepsTest.compareResponseBody(response, "refreshToken");
    }

    @Test
    @DisplayName("Сheck invalid email login")
    @Description("Сhecking the login with invalid email")
    public void checkUserLoginWithInvalidLogin() {
        Response response =
                userClient.login(userRegisterRequestBody);
        stepsTest.compareResponseStatusCode(response, SC_UNAUTHORIZED);
        stepsTest.compareResponseBody(response, "success", false);
        stepsTest.compareResponseBody(response, "message", "email or password are incorrect");
    }
    @Test
    @DisplayName("Сheck invalid password login")
    @Description("Сhecking the login with invalid password")
    public void checkUserLoginWithInvalidPassword() {
        userClient.create(userRegisterRequestBody);
        userRegisterRequestBody.setPassword("P@ssw0rd");
        Response response =
                userClient.login(userRegisterRequestBody);
        stepsTest.compareResponseStatusCode(response, SC_UNAUTHORIZED);
        stepsTest.compareResponseBody(response, "success", false);
        stepsTest.compareResponseBody(response, "message", "email or password are incorrect");
    }
}
