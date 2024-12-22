package praktikum;

import data.Data;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.UserSteps;

import static data.Data.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class EditUserDataTest {

    public UserSteps userSteps;
    public Response response;
    public String token;;


    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        userSteps = new UserSteps();
        response = userSteps.createUser(VALID_UNIQUE_USER_REQUEST_BODY);
        userSteps.getAccessToken(response);
        token = userSteps.accessToken;
    }

    @Test
    public void editUserDataWithAuthorizationEmailChange(){
        userSteps.editUserDataWithAuthorization(token,USER_DATA_UPDATE_BODIES.get(0))
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("user.email", equalTo("pomenyali@pomenyalkin.ru"))
                .body("user.name", equalTo(name));

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
