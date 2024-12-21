package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserSteps {

    public String accessToken;

    @Step("Создание уникального пользователя с заданным телом запроса")
    public Response createUser(String requestBody) {
        return given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .post("/api/auth/register");
    }

    @Step("Логин пользователя с заданным телом запроса")
    public Response loginUser(String loginRequestBody) {
        return given()
                .header("Content-type", "application/json")
                .body(loginRequestBody)
                .when()
                .post("/api/auth/login");
    }

    @Step("Удаление пользователя по accessToken")
    public Response deleteUser() {
        return given()
                .header("Authorization",accessToken)
                .header("Content-Type", "application/json")
                .when()
                .delete("/api/auth/user");
    }

    @Step("Извлечение accessToken")
    public void getAccessTokenAfterCreate(Response response) {
        this.accessToken = response.jsonPath().getString("accessToken");
    }
}

