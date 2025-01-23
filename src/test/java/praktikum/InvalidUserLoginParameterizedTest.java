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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import steps.UserSteps;

import static data.OrderData.BASE_URL;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.apache.http.HttpStatus.*;

@Epic("Логин пользователя")
@RunWith(Parameterized.class)
public class InvalidUserLoginParameterizedTest {

    private final int index;
    private UserSteps userSteps;
    private Response uniqueUserCreating;
    private Response response;

    public InvalidUserLoginParameterizedTest(int index) {
        this.index = index;
    }

    @Parameterized.Parameters(name = "Тест - логин с неверным логином и паролем, индекс: {0}")
    public static Object[] invalidUserIndices() {

        return new Object[]{0, 1, 2};
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        userSteps = new UserSteps();
        uniqueUserCreating = userSteps.createUser(UserData.getValidUser());
    }

    @Test
    @DisplayName("Попытка логина с некорректными данными")
    @Description("Этот тест проверяет, что при логине с пустым email, паролем или именем возвращается ошибка")
    public void userLoginWithInvalidData() {

        response = userSteps.loginUser(UserData.getInvalidLoginRequests().get(index));


        response.then()
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }

    @After
    public void tearDown() {
        if (uniqueUserCreating != null) {
            userSteps.getAccessToken(uniqueUserCreating);
            userSteps.deleteUser();
        }
    }
}
