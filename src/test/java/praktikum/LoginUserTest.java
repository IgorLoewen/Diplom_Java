package praktikum;

import data.UserData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.UserSteps;

import static data.OrderData.BASE_URL;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.apache.http.HttpStatus.*;

public class LoginUserTest {

    public UserSteps userSteps;
    public Response response;
    public Response uniqueUserCreating;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        userSteps = new UserSteps();
        uniqueUserCreating = userSteps.createUser(UserData.getValidUser());
    }

    @Test // логин под существующим пользователем
    public void userLogin() {
        response = userSteps.loginUser(UserData.getValidLogin());

        response.then()
                .statusCode(SC_OK)
                .body("success", equalTo(true));
    }


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
