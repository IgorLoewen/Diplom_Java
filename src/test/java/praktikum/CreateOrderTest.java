package praktikum;

import data.Data;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import steps.OrderSteps;
import steps.UserSteps;

import java.util.List;
import java.util.Map;

import static data.Data.*;
import static data.OrderData.BASE_URL;
import static io.restassured.RestAssured.given;
import static java.util.function.Predicate.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

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
        response = userSteps.createUser(VALID_UNIQUE_USER_REQUEST_BODY);
        userSteps.getAccessToken(response);
        token = userSteps.accessToken;
    }


    @Test  // Создание заказа с авторизацией
    // Надо будет уточнить серую зону.  По заданию нужно сделать 2 теста. Один с авторизацией, жругой с ингридиентами.
    // или я что то не понимаю или это одно и тоже. Надо уточнить
    public void createOrderWithAuthorization(){

        orderSteps.createOrderWithAuthorization(token,ORDER_BODIES.get(5))

                .then().statusCode(200)
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

    @Test  // Создание заказа без авторизации
           // Отсутствует документация для этого случая
           // Сделал проверку по факту ответа и исходя из логики, что в задании есть
           // проверка, что бы проверить заказы не авторизированного пользователя
    public void createOrderWithoutAuthorization(){

        orderSteps.createOrderWithoutAuthorization(ORDER_BODIES.get(5))

                .then().statusCode(200)
                .body("success", equalTo(true))
                .body("name", notNullValue())
                .body("order", notNullValue());
    }

    @Test  // Создание заказа без ингридиентов
    public void createOrderWithoutIngredients(){

        orderSteps.createOrderWithAuthorization(token,EMPTY_INGREDIENTS)

                .then().statusCode(400)
                .body("success", equalTo(false))
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test  // Создание заказа с неверным Hash ингридиента
           // СЕРАЯ ЗОНА.  НАС УЧИЛИ ЧТО МЫ НИКОГДА 500 не ожидаем!!!!!
    public void createOrderWithWrongHashForIngredients(){

        orderSteps.createOrderWithAuthorization(token,INVALID_HASH_INGREDIENT)

                .then().statusCode(500);
    }



    @After
    public void tearDown() {
        if (response != null) {
            userSteps.getAccessToken(response);
            userSteps.deleteUser();
        }
        response = null;
        token = null;
    }
}
