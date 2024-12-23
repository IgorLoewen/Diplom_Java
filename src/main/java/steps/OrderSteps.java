package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderSteps {

    @Step("Создание заказа с авторизацией")
    public Response createOrderWithAuthorization(String accessToken, String requestBody) {
        return given()
                .header("Authorization", accessToken)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/api/orders");
    }

    @Step("Создание заказа без авторизации")
    public Response createOrderWithoutAuthorization(String requestBody) {
        return given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/api/orders");
    }

    @Step("Получение списка заказов c авторизацией")
    public Response getOrderListAuthorizedUser(String token) {
        return given()
                .header("Authorization", token)
                .when()
                .get("/api/orders");

    }
}
