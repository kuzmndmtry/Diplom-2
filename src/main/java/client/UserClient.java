package client;

import dto.user.UserLoginRequestBody;
import dto.user.UserRegisterRequestBody;
import dto.user.UserUpdateRequestBody;
import io.restassured.response.Response;
import steps.StepsUser;

public class UserClient {
    private final StepsUser stepsUser;

    public UserClient(StepsUser stepsUser) {
        this.stepsUser = stepsUser;
    }
    public Response create(UserRegisterRequestBody userRegisterRequestBody) {
        return stepsUser.sendPostRequestUserRegister(userRegisterRequestBody);
    }
    public Response login(String email, String password) {
        UserLoginRequestBody userLoginRequestBodyRequest = new UserLoginRequestBody();
        userLoginRequestBodyRequest.setEmail(email);
        userLoginRequestBodyRequest.setPassword(password);
        return stepsUser.sendPostRequestUserLogin(userLoginRequestBodyRequest);
    }
    public Response login(UserRegisterRequestBody userRegisterRequestBody) {
        return stepsUser.sendPostRequestUserLogin(userRegisterRequestBody);
    }

    public Response delete(String authorizationToken){
        return stepsUser.sendDeleteRequestUser(authorizationToken);
    }

    public Response patch(String authorizationToken, UserUpdateRequestBody userUpdateRequestBody) {
        return stepsUser.sendPatchRequestUser(authorizationToken, userUpdateRequestBody);
    }
    public Response patch(UserUpdateRequestBody userUpdateRequestBody) {
        return stepsUser.sendPatchRequestUser(userUpdateRequestBody);
    }



}
