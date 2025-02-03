package praktikum;

import data.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.UserModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.UserSteps;

import static data.OrderData.BASE_URL;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.apache.http.HttpStatus.*;

@Epic("User Creation")
public class CreateUserTest {

    public UserSteps userSteps;
    public Response response;


    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        userSteps = new UserSteps();
    }

    @Test
    @DisplayName("Creating a unique user")
    @Description("This test verifies the ability to create a user with valid data.")
    public void uniqueUserCreating() {

        response = userSteps.createUser(UserData.getValidUser());

        response.then()
                .statusCode(SC_OK)
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Creating a duplicate user")
    @Description("This test verifies that registering the same user again returns an error.")
    public void duplicateUserCreationReturnsError() {
        UserModel fixedUser = UserData.getValidUser();

        response = userSteps.createUser(fixedUser);

        Response responseSecondCreation = userSteps.createUser(fixedUser);
        responseSecondCreation.then()
                .statusCode(SC_FORBIDDEN)
                .body("success", equalTo(false))
                .body("message", equalTo("User already exists"));
    }


    @After
    public void tearDown() {
        if (response != null) {
            userSteps.getAccessToken(response);
            userSteps.deleteUser();
        }
    }
}
