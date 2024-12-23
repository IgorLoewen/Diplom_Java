package praktikum;

import data.Data;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.UserSteps;

import static data.Data.VALID_UNIQUE_USER_REQUEST_BODY;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateOrderTest {

    public UserSteps userSteps;
    public Response response;
    public Response uniqueUserCreating;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        userSteps = new UserSteps();
        uniqueUserCreating = userSteps.createUser(VALID_UNIQUE_USER_REQUEST_BODY);
    }
@Test



    @After
    public void tearDown() {
        if (uniqueUserCreating != null) {
            userSteps.getAccessToken(uniqueUserCreating);
            userSteps.deleteUser();
        }
        uniqueUserCreating = null;
        response = null;
    }
}
