package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.UserModel;

import static data.OrderData.*;
import static io.restassured.RestAssured.given;

public class UserSteps {

    public String accessToken;

    @Step("Создание уникального пользователя с заданным телом запроса")
    public Response createUser(UserModel userModel) {
        return given()
                .header("Content-type", "application/json")
                .body(userModel)
                .when()
                .post(REGISTER_URL);
    }

    @Step("Логин пользователя с заданным телом запроса")
    public Response loginUser(UserModel userModel) {
        return given()
                .header("Content-type", "application/json")
                .body(userModel)
                .when()
                .post(LOGIN_URL);
    }

    @Step("Удаление пользователя по accessToken")
    public Response deleteUser() {
        return given()
                .header("Authorization",accessToken)
                .header("Content-Type", "application/json")
                .when()
                .delete(AUTH_URL);
    }

    @Step("Извлечение accessToken ")
    public void getAccessToken(Response response) {
        this.accessToken = response.jsonPath().getString("accessToken");
    }

    @Step("Изменение данных пользователя с авторизацией")
    public Response editUserDataWithAuthorization(String accessToken, UserModel userModel) {
        return given()
                .header("Authorization",accessToken)
                .header("Content-Type", "application/json")
                .body(userModel)
                .when()
                .patch(AUTH_URL);
    }

    @Step("Изменение данных пользователя без авторизации")
    public Response editUserDataWithoutAuthorization(UserModel userModel) {
        return given()
                .header("Content-Type", "application/json")
                .body(userModel)
                .when()
                .patch(AUTH_URL);
    }

}

