package user;

import client.UserClient;
import dto.user.UserLoginResponseBody;
import dto.user.UserRegisterRequestBody;
import dto.user.UserUpdateRequestBody;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.StepsTest;
import steps.StepsUser;

import java.util.Locale;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

public class PatchUserTest {
    private UserClient userClient;
    private UserRegisterRequestBody userRegisterRequestBody;
    private UserUpdateRequestBody userUpdateRequestBody;
    StepsTest stepsTest = new StepsTest();

    @Before
    public void setUp() {
        userClient = new UserClient(new StepsUser());
        userRegisterRequestBody = new UserRegisterRequestBody();
        userUpdateRequestBody = new UserUpdateRequestBody();
    }

    @After
    public void deleteTestCourier() {
        UserLoginResponseBody userLoginResponseBody =
                userClient.login(userRegisterRequestBody)
                        .as(UserLoginResponseBody.class);
        if (userLoginResponseBody.getAccessToken() != null) {
            userClient.delete(userLoginResponseBody.getAccessToken());
        }
        UserLoginResponseBody userLoginResponseBodyWihUpdateEmail =
                userClient.login(userUpdateRequestBody.getEmail(), userRegisterRequestBody.getPassword())
                        .as(UserLoginResponseBody.class);
        if (userLoginResponseBodyWihUpdateEmail.getAccessToken() != null) {
            userClient.delete(userLoginResponseBodyWihUpdateEmail.getAccessToken());
        }
    }

    @Test
    @DisplayName("Сheck update user email")
    @Description("Сhecking the update user email")
    public void checkUpdateUserEmailWithAuth() {
        userClient.create(userRegisterRequestBody);
        UserLoginResponseBody userLoginResponseBody =
                userClient.login(userRegisterRequestBody)
                        .as(UserLoginResponseBody.class);
        userUpdateRequestBody.setName(null);
        Response response =
                userClient.patch(userLoginResponseBody.getAccessToken(), userUpdateRequestBody);
        stepsTest.compareResponseStatusCode(response, SC_OK);
        stepsTest.compareResponseBody(response, "success", true);
        stepsTest.compareResponseBody(response, "user.email", userUpdateRequestBody.getEmail()
                .toLowerCase(Locale.ROOT));
        stepsTest.compareResponseBody(response, "user.name", userRegisterRequestBody.getName());
    }

    @Test
    @DisplayName("Сheck update user name")
    @Description("Сhecking the update user name")
    public void checkUpdateUserNameWithAuth() {
        userClient.create(userRegisterRequestBody);
        UserLoginResponseBody userLoginResponseBody =
                userClient.login(userRegisterRequestBody)
                        .as(UserLoginResponseBody.class);
        userUpdateRequestBody.setEmail(null);
        Response response =
                userClient.patch(userLoginResponseBody.getAccessToken(), userUpdateRequestBody);
        stepsTest.compareResponseStatusCode(response, SC_OK);
        stepsTest.compareResponseBody(response, "success", true);
        stepsTest.compareResponseBody(response, "user.email", userRegisterRequestBody.getEmail()
                .toLowerCase(Locale.ROOT));
        stepsTest.compareResponseBody(response, "user.name", userUpdateRequestBody.getName());
    }

    @Test
    @DisplayName("Сheck update user email without auth")
    @Description("Сhecking the update user email without auth")
    public void checkUpdateUserEmailWithoutAuth() {
        userClient.create(userRegisterRequestBody);
        userUpdateRequestBody.setName(null);
        Response response =
                userClient.patch(userUpdateRequestBody);
        stepsTest.compareResponseStatusCode(response, SC_UNAUTHORIZED);
        stepsTest.compareResponseBody(response, "success", false);
        stepsTest.compareResponseBody(response, "message", "You should be authorised");
    }

    @Test
    @DisplayName("Сheck update user email without auth")
    @Description("Сhecking the update user email without auth")
    public void checkUpdateUserNameWithoutAuth() {
        userClient.create(userRegisterRequestBody);
        userUpdateRequestBody.setEmail(null);
        Response response =
                userClient.patch(userUpdateRequestBody);
        stepsTest.compareResponseStatusCode(response, SC_UNAUTHORIZED);
        stepsTest.compareResponseBody(response, "success", false);
        stepsTest.compareResponseBody(response, "message", "You should be authorised");
    }
}
