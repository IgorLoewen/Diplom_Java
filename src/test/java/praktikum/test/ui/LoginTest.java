package praktikum.test.ui;

import data.UserData;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.LoginPage;
import pages.RegisterPage;
import steps.UserSteps;


@Epic("Регистрация пользователя")
@RunWith(Parameterized.class)
public class LoginTest extends TestsSetUp {

    public UserSteps userSteps;
    public Response loginResponse;

    public LoginTest(String browser) {
        super(browser);
    }

    @Before
    @Step("Подготовка окружения для теста")
    @Description("Создаёт окружение, открывает страницу регистрации и инициализирует UserSteps")
    public void setUp() {
        super.setUp();
        userSteps = new UserSteps();
        driver.get(RegisterPage.REGISTER_URL);
        loginResponse = userSteps.createUser(UserData.getValidUser());
    }

    @Test


    @After
    public void tearDown() {
        if (loginResponse != null) {
            userSteps.getAccessToken(loginResponse);
            userSteps.deleteUser();
            loginResponse = null;
        }
    }
}
