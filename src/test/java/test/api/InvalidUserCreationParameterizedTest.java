package test.api;

import data.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import steps.UserSteps;

import static data.OrderData.BASE_URL;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.apache.http.HttpStatus.*;

@Epic("User Creation")
@RunWith(Parameterized.class)
public class InvalidUserCreationParameterizedTest {

    private final int index;
    private UserSteps userSteps;
    private Response response;


    public InvalidUserCreationParameterizedTest(int index) {
        this.index = index;
    }

    @Parameterized.Parameters(name = "Test - create a user without filling in one of the required fields, index: {0}")
    public static Object[] invalidUserIndices() {

        return new Object[]{0, 1, 2};
    }

    @Before
    public void setUp() {

        RestAssured.baseURI = BASE_URL;
        userSteps = new UserSteps();
    }

    @Test
    @DisplayName("Creating a user with a missing required field")
    @Description("This test verifies that attempting to create a user without an email, password, or name returns an error.")
    public void shouldReturnErrorForInvalidUser() {

        response = userSteps.createUser(UserData.getInvalidUserRequests().get(index));

        response.then()
                .statusCode(SC_FORBIDDEN)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }

}
