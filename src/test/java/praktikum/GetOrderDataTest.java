package praktikum;

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
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@Epic("Получение данных заказов")
public class GetOrderDataTest {

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
    @DisplayName("Получение заказов авторизованного пользователя")
    @Description("Этот тест проверяет возможность получения списка заказов конкретного пользователя с авторизацией")
    public void getOrderListForAuthorizedUser() {

        orderSteps.getOrderListAuthorizedUser(token)

                .then()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("orders", notNullValue())
                .body("orders.size()", notNullValue());
    }

    @Test
    @DisplayName("Получение всех заказов неавторизованным пользователем")
    @Description("Этот тест проверяет возможность получения полного списка заказов без авторизации")
    public void getFullOrderListForNotAuthorizedUser() {

        orderSteps.getFullOrderListNotAuthorizedUser()

                .then()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("orders", notNullValue())
                .body("total", notNullValue())
                .body("totalToday", notNullValue());
    }

    @After
    public void tearDown() {
        if (response != null) {
            userSteps.getAccessToken(response);
            userSteps.deleteUser();
        }
    }
}
