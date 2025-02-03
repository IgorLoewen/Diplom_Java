package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.OrderModel;

import static data.UserData.ORDER_URL;
import static io.restassured.RestAssured.given;

public class OrderSteps {

    @Step("Creating an order with authorization")
    public Response createOrderWithAuthorization(String accessToken, OrderModel orderModel) {
        return given()
                .header("Authorization", accessToken)
                .header("Content-Type", "application/json")
                .body(orderModel)
                .when()
                .post(ORDER_URL);
    }

    @Step("Creating an order without authorization")
    public Response createOrderWithoutAuthorization(OrderModel orderModel) {
        return given()
                .header("Content-Type", "application/json")
                .body(orderModel)
                .when()
                .post(ORDER_URL);
    }

    @Step("Retrieving the list of orders with authorization")
    public Response getOrderListAuthorizedUser(String token) {
        return given()
                .header("Authorization", token)
                .when()
                .get(ORDER_URL);

    }

    @Step("Retrieving the list of orders without authorization")
    public Response getFullOrderListNotAuthorizedUser() {
        return given()
                .when()
                .get(ORDER_URL);

    }
}
