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
    private  List<Map<String, Object>> ingredients;



    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        userSteps = new UserSteps();
        orderSteps = new OrderSteps();
        response = userSteps.createUser(VALID_UNIQUE_USER_REQUEST_BODY);
        userSteps.getAccessToken(response);
        token = userSteps.accessToken;
    }


    @Test  // Создание заказа с авторизацией
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
