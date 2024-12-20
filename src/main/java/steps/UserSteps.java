package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserSteps {

    @Step("Создание уникального пользователя с заданным телом запроса")
    public Response createUser(String requestBody) {
        return given()
                .header("Content-type", "application/json")
                .body(requestBody) // Используем переданный параметр
                .when()
                .post("/api/auth/register");
    }
}
