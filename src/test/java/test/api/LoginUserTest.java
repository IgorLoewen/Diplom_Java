package test.api;

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
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;

@Epic("User Login")
public class LoginUserTest {

    private UserSteps userSteps;
    private Response response;
    private Response uniqueUserCreating;
    private UserModel fixedUser;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        userSteps = new UserSteps();
        fixedUser = UserData.getValidUser();
        uniqueUserCreating = userSteps.createUser(fixedUser);
    }

    @Test
    @DisplayName("Login with an existing user")
    @Description("This test verifies the ability to log in with the correct credentials of an existing user.")
    public void userLogin() {

        response = userSteps.loginUser(new UserModel(fixedUser.getEmail(), fixedUser.getPassword(), null));

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
    }
}