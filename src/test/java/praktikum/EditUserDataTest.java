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

public class EditUserDataTest {

    public UserSteps userSteps;
    public Response response;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        userSteps = new UserSteps();
        userSteps.createUser(VALID_UNIQUE_USER_REQUEST_BODY);
    }

    @Test
    public void editUserDataWithAuthorization(){

    }




    @After
    public void tearDown() {
        if (response != null) {
            userSteps.getAccessToken(response);
            userSteps.deleteUser();
            response = null;
        }
    }
}
