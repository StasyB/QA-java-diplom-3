package ru.yandex.practicum.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.practicum.client.base.ApiClient;
import ru.yandex.practicum.model.user.User;
import ru.yandex.practicum.model.user.UserCredentials;

import static io.restassured.RestAssured.given;

public class UserSteps extends ApiClient {
    private static final String USER_REGISTER = "/auth/register";
    private static final String USER_LOGIN = "/auth/login";
    private static final String USER_CHANGE_DELETE = "/auth/user";

    @Step
    public ValidatableResponse register(User user) {
        return given()
                .spec(getBaseReqSpec())
                .body(user)
                .when()
                .post(USER_REGISTER)
                .then();
    }

    @Step
    public ValidatableResponse login(UserCredentials userCredentials) {
        return given()
                .spec(getBaseReqSpec())
                .body(userCredentials)
                .when()
                .post(USER_LOGIN)
                .then();
    }

    @Step
    public ValidatableResponse delete(String accessToken) {
        return given()
                .spec(getBaseReqSpec())
                .header("Authorization", accessToken)
                .when()
                .delete(USER_CHANGE_DELETE)
                .then();
    }

}