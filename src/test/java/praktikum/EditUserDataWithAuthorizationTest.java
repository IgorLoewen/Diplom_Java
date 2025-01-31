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

@Epic("Изменение данных пользователя")
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
    @DisplayName("Изменение email с авторизацией")
    @Description("Этот тест проверяет, что можно изменить email пользователя с авторизацией")
    public void editUserDataWithAuthorizationEmailChange() {
        UserModel emailUpdate = new UserModel("pomenyali@pomenyalkin.ru", null, null);

        userSteps.editUserDataWithAuthorization(token, emailUpdate)
                .then()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("user.email", equalTo("pomenyali@pomenyalkin.ru"))
                .body("user.name", equalTo(fixedUser.getName()));
    }

    @Test
    @DisplayName("Изменение имени с авторизацией")
    @Description("Этот тест проверяет, что можно изменить имя пользователя с авторизацией")
    public void editUserDataWithAuthorizationNameChange() {
        UserModel nameUpdate = new UserModel(null, null, "Pomenyalkin");

        userSteps.editUserDataWithAuthorization(token, nameUpdate)
                .then()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("user.email", equalTo(fixedUser.getEmail()))
                .body("user.name", equalTo("Pomenyalkin"));
    }

    @After
    public void tearDown() {
        if (response != null) {
            userSteps.getAccessToken(response);
            userSteps.deleteUser();
        }
    }
}
