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

@Epic("Изменение данных пользователя")
public class EditUserDataWithAuthorizationTest {

    public UserSteps userSteps;
    public Response response;
    public String token;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        userSteps = new UserSteps();
        response = userSteps.createUser(UserData.getValidUser());
        userSteps.getAccessToken(response);
        token = userSteps.accessToken;
    }

    @Test
    @DisplayName("Изменение email с авторизацией")
    @Description("Этот тест проверяет, что можно изменить email пользователя с авторизацией. Ожидаемый статус ответа: 200.")
    public void editUserDataWithAuthorizationEmailChange() {

        userSteps.editUserDataWithAuthorization(token, UserData.getUserDataUpdateBodies().get(0))

                .then()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("user.email", equalTo("pomenyali@pomenyalkin.ru"))
                .body("user.name", equalTo(UserData.NAME));
    }

    @Test
    @DisplayName("Изменение пароля с авторизацией")
    @Description("Этот тест проверяет, что можно изменить пароль пользователя с авторизацией. Ожидаемый статус ответа: 200.")
    public void editUserDataWithAuthorizationPasswordChange() {

        userSteps.editUserDataWithAuthorization(token, UserData.getUserDataUpdateBodies().get(1))

                .then()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("user.email", equalTo(UserData.EMAIL))
                .body("user.name", equalTo(UserData.NAME));
    }

    @Test
    @DisplayName("Изменение имени с авторизацией")
    @Description("Этот тест проверяет, что можно изменить имя пользователя с авторизацией. Ожидаемый статус ответа: 200.")
    public void editUserDataWithAuthorizationNameChange() {

        userSteps.editUserDataWithAuthorization(token, UserData.getUserDataUpdateBodies().get(2))

                .then()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("user.email", equalTo(UserData.EMAIL))
                .body("user.name", equalTo("Pomenyalkin"));
    }

    @After
    public void tearDown() {
        if (response != null) {
            userSteps.getAccessToken(response);
            userSteps.deleteUser();
            response = null;
            token = null;
        }
    }
}
