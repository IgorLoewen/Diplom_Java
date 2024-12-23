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
import static org.hamcrest.CoreMatchers.equalTo;

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


        ingredients = orderSteps.getIngredients().jsonPath().getList("data");
        System.out.println(ingredients);
    }


    @Test
    public void createOrderWithAuthorization(){
        orderSteps.createOrderWithAuthorization(token,ORDER_BODIES.get(2));

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
