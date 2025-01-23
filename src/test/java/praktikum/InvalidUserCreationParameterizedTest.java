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

@Epic("Создание пользователя")
@RunWith(Parameterized.class)
public class InvalidUserCreationParameterizedTest {

    private final int index;
    private UserSteps userSteps;
    private Response response;


    public InvalidUserCreationParameterizedTest(int index) {
        this.index = index;
    }

    @Parameterized.Parameters(name = "Тест - создать пользователя и не заполнить одно из обязательных полей, индекс: {0}")
    public static Object[] invalidUserIndices() {

        return new Object[]{0, 1, 2};
    }

    @Before
    public void setUp() {

        RestAssured.baseURI = BASE_URL;
        userSteps = new UserSteps();
    }

    @Test
    @DisplayName("Создание пользователя с отсутствующим обязательным полем")
    @Description("Этот тест проверяет, что при попытке создать пользователя с отсутствующим email, паролем или именем возвращается ошибка")
    public void shouldReturnErrorForInvalidUser() {

        response = userSteps.createUser(UserData.getInvalidUserRequests().get(index));


        response.then()
                .statusCode(SC_FORBIDDEN)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }

}
