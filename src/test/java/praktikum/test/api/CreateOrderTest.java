package praktikum.test.api;

import data.OrderData;
import data.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.OrderSteps;
import steps.UserSteps;

import static data.OrderData.BASE_URL;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@Epic("Order Creation")
public class CreateOrderTest {

    public UserSteps userSteps;
    public Response response;
    public String token;
    public OrderSteps orderSteps;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        userSteps = new UserSteps();
        orderSteps = new OrderSteps();
        response = userSteps.createUser(UserData.getValidUser());
        userSteps.getAccessToken(response);
        token = userSteps.accessToken;
    }

    @Test
    @DisplayName("Creating an order with authorization and ingredients")
    @Description("This test verifies the ability to create an order with authorization. All key response fields are checked, including ID, status, and price.")
    public void createOrderWithAuthorization() {

        orderSteps.createOrderWithAuthorization(token, OrderData.getOrderBodies().get(5))

                .then()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("name", notNullValue())
                .body("order", notNullValue())
                .body("order._id", notNullValue())
                .body("order.owner", notNullValue())
                .body("order.status", equalTo("done"))
                .body("order.name", notNullValue())
                .body("order.createdAt", notNullValue())
                .body("order.updatedAt", notNullValue())
                .body("order.number", notNullValue())
                .body("order.price", notNullValue());
    }

    @Test
    @DisplayName("Creating an order with authorization and ingredients")
    @Description("This test verifies the ability to create an order with authorization. All key response fields are checked, including ID, status, and price.")
    public void createOrderWithoutAuthorization() {

        orderSteps.createOrderWithoutAuthorization(OrderData.getOrderBodies().get(4))

                .then()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("name", notNullValue())
                .body("order", notNullValue());
    }

    @Test
    @DisplayName("Creating an order without ingredients")
    @Description("This test verifies that creating an order without specifying ingredients returns an error with code 400.")
    public void createOrderWithoutIngredients() {

        orderSteps.createOrderWithAuthorization(token, OrderData.getEmptyIngredients())

                .then()
                .statusCode(SC_BAD_REQUEST)
                .body("success", equalTo(false))
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Creating an order with an invalid ingredient hash")
    @Description("This test verifies that creating an order with an invalid ingredient hash returns an error with code 500. This is an edge case, as a 500 error is usually not expected.")
    public void createOrderWithWrongHashForIngredients() {

        orderSteps.createOrderWithAuthorization(token, OrderData.getInvalidHashIngredient())

                .then()
                .statusCode(SC_INTERNAL_SERVER_ERROR);
    }

    @After
    public void tearDown() {
        if (response != null) {
            userSteps.getAccessToken(response);
            userSteps.deleteUser();
        }
    }
}
