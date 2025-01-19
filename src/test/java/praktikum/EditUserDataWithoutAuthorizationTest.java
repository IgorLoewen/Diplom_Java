package praktikum;

import data.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.UserModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import steps.UserSteps;

import java.util.List;

import static data.OrderData.BASE_URL;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.CoreMatchers.equalTo;

@Epic("Изменение данных пользователя")
@RunWith(Parameterized.class)
public class EditUserDataWithoutAuthorizationTest {

    private final UserModel userData;
    private UserSteps userSteps;

    public EditUserDataWithoutAuthorizationTest(UserModel userData) {
        this.userData = userData;
    }

    @Parameterized.Parameters(name = "Тест с набором данных: {0}")
    public static List<UserModel> userData() {

        return UserData.getUserDataUpdateBodies();
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        userSteps = new UserSteps();
    }

    @Test
    @DisplayName("Изменение данных пользователя без авторизации")
    @Description("Этот тест проверяет, что при попытке изменить данные пользователя без авторизации возвращается ошибка.")
    public void shouldReturnErrorWhenEditingWithoutAuthorization() {

        Response response = userSteps.editUserDataWithoutAuthorization(userData);

        response.then()
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised")); // Сообщение об ошибке
    }
}
