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
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@Epic("Retrieving Order Data")
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
    @DisplayName("Retrieving orders of an authorized user")
    @Description("This test verifies the ability to retrieve the list of orders for a specific user with authorization.")
    public void getOrderListForAuthorizedUser() {

        orderSteps.getOrderListAuthorizedUser(token)

                .then()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("orders", notNullValue())
                .body("orders.size()", notNullValue());
    }

    @Test
    @DisplayName("Attempt to retrieve orders of a specific user by an unauthorized user")
    @Description("This test verifies that an unauthorized user cannot retrieve orders of a specific user and receives a 401 error.")
    public void getFullOrderListForNotAuthorizedUser() {

        orderSteps.getFullOrderListNotAuthorizedUser()

                .then()
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }

    @After
    public void tearDown() {
        if (response != null) {
            userSteps.getAccessToken(response);
            userSteps.deleteUser();
        }
    }
}
