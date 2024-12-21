package praktikum;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import steps.UserSteps;

import static data.Data.LOGIN_REQUEST_BODY;
import static data.Data.VALID_UNIQUE_USER_REQUEST_BODY;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateUserTest{

    public UserSteps userSteps;

    public String accessToken;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
       userSteps = new UserSteps();
    }

    @Test
    public void uniqueUserCreating() {

        Response createResponse = userSteps.createUser(VALID_UNIQUE_USER_REQUEST_BODY);
        createResponse.then().statusCode(200).body("success", equalTo(true));
        userSteps.getAccessTokenAfterCreate(createResponse);
        Response deleteResponse = userSteps.deleteUser();
        deleteResponse.then().statusCode(202).body("success", equalTo(true));
    }


}
