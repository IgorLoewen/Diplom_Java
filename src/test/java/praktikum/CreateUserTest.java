package praktikum;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.UserSteps;

import static data.Data.VALID_UNIQUE_USER_REQUEST_BODY;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateUserTest{

    public UserSteps userSteps;
    public Response response;
    public Response deleteResponse;


   @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        userSteps = new UserSteps();
        response = null;
        deleteResponse = null;
    }

    @Test // создать уникального пользователя;
    public void uniqueUserCreating() {
        response = userSteps.createUser(VALID_UNIQUE_USER_REQUEST_BODY);

        response.then()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Test // создать пользователя, который уже зарегистрирован;
    public void duplicateUserCreationReturnsError() {
        response = userSteps.createUser(VALID_UNIQUE_USER_REQUEST_BODY);

        Response responseSecondCreation = userSteps.createUser(VALID_UNIQUE_USER_REQUEST_BODY);
        responseSecondCreation.then()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo("User already exists"));

    }


    @After
    public void tearDown(){
        userSteps.getAccessToken(response);

        deleteResponse = userSteps.deleteUser();
        deleteResponse.then()
                .statusCode(202)
                .body("success", equalTo(true));
    }


}
