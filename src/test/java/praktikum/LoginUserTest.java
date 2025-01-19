package praktikum;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.UserSteps;

import static data.Data.*;
import static data.OrderData.BASE_URL;
import static org.hamcrest.CoreMatchers.equalTo;

public class LoginUserTest {

    public UserSteps userSteps;
    public Response response;
    public Response uniqueUserCreating;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        userSteps = new UserSteps();
        uniqueUserCreating = userSteps.createUser(VALID_UNIQUE_USER_REQUEST_BODY);
    }

    @Test // логин под существующим пользователем
    public void userLogin() {
        response = userSteps.loginUser(LOGIN_REQUEST_BODY);

        response.then()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Test // попытка логина с неверным email
    public void userLoginWithWrongEmail() {
        response = userSteps.loginUser(INVALID_LOGIN_REQUEST_BODIES.get(0));

        response.then()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }

    @Test // попытка логина с неверным паролем
    public void userLoginWithWrongPassword() {
        response = userSteps.loginUser(INVALID_LOGIN_REQUEST_BODIES.get(1));

        response.then()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }

    @Test // попытка логина с неверным паролем и логином
    public void userLoginWithWrongPasswordAndLogin() {
        response = userSteps.loginUser(INVALID_LOGIN_REQUEST_BODIES.get(2));

        response.then()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
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
