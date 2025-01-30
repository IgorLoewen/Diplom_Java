package praktikum;

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

@Epic("Создание заказа")
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
    @DisplayName("Создание заказа с авторизацией и с ингредиентами")
    @Description("Этот тест проверяет возможность создания заказа с авторизацией. Проверяются все основные поля ответа, включая ID, статус и цену.")
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
    @DisplayName("Создание заказа без авторизации")
    @Description("Этот тест проверяет возможность создания заказа без авторизации. Проверяется успешный ответ с полями name и order.")
    public void createOrderWithoutAuthorization() {

        orderSteps.createOrderWithoutAuthorization(OrderData.getOrderBodies().get(4))

                .then()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("name", notNullValue())
                .body("order", notNullValue());
    }

    @Test
    @DisplayName("Создание заказа без ингредиентов")
    @Description("Этот тест проверяет, что создание заказа без указания ингредиентов возвращает ошибку с кодом 400.")
    public void createOrderWithoutIngredients() {

        orderSteps.createOrderWithAuthorization(token, OrderData.getEmptyIngredients())

                .then()
                .statusCode(SC_BAD_REQUEST)
                .body("success", equalTo(false))
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Создание заказа с неверным Hash ингредиента")
    @Description("Этот тест проверяет, что создание заказа с неверным Hash ингредиента возвращает ошибку с кодом 500. Это серый случай, так как 500 обычно не ожидается.")
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
