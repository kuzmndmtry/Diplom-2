package steps;

import client.RestClient;
import dto.user.UserLoginRequestBody;
import dto.user.UserRegisterRequestBody;
import dto.user.UserUpdateRequestBody;
import io.restassured.response.Response;
import io.qameta.allure.Step;

public class StepsUser extends RestClient {
    private static final String USER_REGISTER = "/api/auth/register"; // эндпоинт для регистрации
    private static final String USER_LOGIN = "/api/auth/login"; // эндпоинт для авторизации
    private static final String USER_LOGOUT = "/api/auth/login"; // эндпоинт для для выхода из системы
    private static final String USER_TOKEN = "/api/auth/token"; // эндпоинт для обновления токена
    private static final String USER_INFO = "/api/auth/user"; // эндпоинт для получения/обновления/удаления данных о пользователе

    @Step("Send POST request to /api/auth/register")
    public Response sendPostRequestUserRegister(UserRegisterRequestBody userRegisterRequestBody) {
        Response response =
                getDefaultRequestSpecification()
                        .body(userRegisterRequestBody)
                        .post(USER_REGISTER);
        return response;
    }
    @Step("Send POST request to /api/auth/login")
    public Response sendPostRequestUserLogin(UserLoginRequestBody userLoginRequestBody) {
        Response response =
                getDefaultRequestSpecification()
                        .body(userLoginRequestBody)
                        .post(USER_LOGIN);
        return response;
    }
    @Step("Send POST request to /api/auth/login")
    public Response sendPostRequestUserLogin(UserRegisterRequestBody userRegisterRequestBody) {
        Response response =
                getDefaultRequestSpecification()
                        .body(userRegisterRequestBody)
                        .post(USER_LOGIN);
        return response;
    }
    @Step("Send DELETE request to /api/auth/user")
    public Response sendDeleteRequestUser(String authorizationToken) {
        Response response =
                (Response) getDefaultRequestSpecification()
                        .header("Authorization", authorizationToken)
                        .delete(USER_INFO);
        return response;
    }
    @Step("Send PACH request to /api/auth/user")
    public Response sendPatchRequestUser(String authorizationToken, UserUpdateRequestBody userUpdateRequestBody){
        Response response =
                getDefaultRequestSpecification()
                        .header("Authorization", authorizationToken)
                        .body(userUpdateRequestBody)
                        .patch(USER_INFO);
        return response;
    }
    @Step("Send PACH request to /api/auth/user")
    public Response sendPatchRequestUser(UserUpdateRequestBody userUpdateRequestBody){
        Response response =
                getDefaultRequestSpecification()
                        .body(userUpdateRequestBody)
                        .patch(USER_INFO);
        return response;
    }


}
