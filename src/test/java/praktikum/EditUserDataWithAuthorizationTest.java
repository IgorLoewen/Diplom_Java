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
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;

@Epic("User Data Modification")
public class EditUserDataWithAuthorizationTest {

    private UserSteps userSteps;
    private Response response;
    private String token;
    private UserModel fixedUser;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        userSteps = new UserSteps();
        fixedUser = UserData.getValidUser();
        response = userSteps.createUser(fixedUser);
        userSteps.getAccessToken(response);
        token = userSteps.accessToken;
    }

    @Test
    @DisplayName("Updating email with authorization")
    @Description("This test verifies that the user's email can be updated with authorization.")
    public void editUserDataWithAuthorizationEmailChange() {
        UserModel emailUpdate = new UserModel("change@chanched.com", null, null);

        userSteps.editUserDataWithAuthorization(token, emailUpdate)
                .then()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("user.email", equalTo("change@chanched.com"))
                .body("user.name", equalTo(fixedUser.getName()));
    }

    @Test
    @DisplayName("Updating name with authorization")
    @Description("This test verifies that the user's name can be updated with authorization.")
    public void editUserDataWithAuthorizationNameChange() {
        UserModel nameUpdate = new UserModel(null, null, "ChangeMan");

        userSteps.editUserDataWithAuthorization(token, nameUpdate)
                .then()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("user.email", equalTo(fixedUser.getEmail()))
                .body("user.name", equalTo("ChangeMan"));
    }

    @After
    public void tearDown() {
        if (response != null) {
            userSteps.getAccessToken(response);
            userSteps.deleteUser();
        }
    }
}
