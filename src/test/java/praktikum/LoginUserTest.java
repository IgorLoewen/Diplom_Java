package praktikum;

import data.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.UserSteps;

import static data.OrderData.BASE_URL;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;

@Epic("Логин пользователя")
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

    @Test
    @DisplayName("Логин под существующим пользователем")
    @Description("Этот тест проверяет возможность логина с корректными данными существующего пользователя")
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
