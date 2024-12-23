package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderSteps {

    @Step("Создание заказа с авторизацией")
    public Response createOrderWithAuthorization(String accessToken, String requestBody) {
        return given()
                .header("Authorization",accessToken)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/api/orders");


    }
}
