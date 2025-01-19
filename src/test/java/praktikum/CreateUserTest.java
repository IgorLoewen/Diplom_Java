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
import static org.hamcrest.CoreMatchers.equalTo;
import static org.apache.http.HttpStatus.*;

@Epic("Создание пользователя")
public class CreateUserTest {

    public UserSteps userSteps;
    public Response response;


    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        userSteps = new UserSteps();
    }

    @Test
    @DisplayName("Создание уникального пользователя")
    @Description("Этот тест проверяет возможность создания пользователя с валидными данными.")
    public void uniqueUserCreating() {
        response = userSteps.createUser(UserData.getValidUser());

        response.then()
                .statusCode(SC_OK)
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Создание дублирующегося пользователя")
    @Description("Этот тест проверяет, что при повторной регистрации одного и того же пользователя возвращается ошибка.")
    public void duplicateUserCreationReturnsError() {
        response = userSteps.createUser(UserData.getValidUser());

        Response responseSecondCreation = userSteps.createUser(UserData.getValidUser());
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
            response = null;
        }
    }
}
