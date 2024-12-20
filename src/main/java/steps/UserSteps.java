package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserSteps {

    private String accessToken;

    @Step("Создание уникального пользователя с заданным телом запроса")
    public Response createUser(String requestBody) {

        Response response = given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .post("/api/auth/register");
        accessToken = response.jsonPath().getString("accessToken");
        return response;

    }

    @Step("Логин пользователя с заданным телом запроса и получение accessToken")
    public Response loginUser(String loginRequestBody) {

        Response response = given()
                .header("Content-type", "application/json")
                .body(loginRequestBody)
                .when()
                .post("/api/auth/login");
        accessToken = response.jsonPath().getString("accessToken");
        return response;
    }

    @Step("Удаление пользователя по accessToken")
    public Response deleteUser() {
        return given()
                .header("Authorization",accessToken)
                .header("Content-Type", "application/json")
                .when()
                .delete("/api/auth/user");
    }



}
