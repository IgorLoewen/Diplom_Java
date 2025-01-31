package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.OrderModel;

import static data.UserData.ORDER_URL;
import static io.restassured.RestAssured.given;

public class OrderSteps {

    @Step("Создание заказа с авторизацией")
    public Response createOrderWithAuthorization(String accessToken, OrderModel orderModel) {
        return given()
                .header("Authorization", accessToken)
                .header("Content-Type", "application/json")
                .body(orderModel)
                .when()
                .post(ORDER_URL);
    }

    @Step("Создание заказа без авторизации")
    public Response createOrderWithoutAuthorization(OrderModel orderModel) {
        return given()
                .header("Content-Type", "application/json")
                .body(orderModel)
                .when()
                .post(ORDER_URL);
    }

    @Step("Получение списка заказов c авторизацией")
    public Response getOrderListAuthorizedUser(String token) {
        return given()
                .header("Authorization", token)
                .when()
                .get(ORDER_URL);

    }

    @Step("Получение списка заказов без авторизации")
    public Response getFullOrderListNotAuthorizedUser() {
        return given()
                .when()
                .get(ORDER_URL);

    }
}
