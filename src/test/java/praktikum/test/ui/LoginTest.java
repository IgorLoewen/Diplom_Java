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
import pages.MainPage;
import steps.UserSteps;

import static org.junit.Assert.assertEquals;


@Epic("Логин пользователя")
@RunWith(Parameterized.class)
public class LoginTest extends TestsSetUp {

    public UserSteps userSteps;
    public Response loginResponse;

    public LoginTest(String browser) {
        super(browser);
    }

    @Before
    @Step("Подготовка окружения для теста")
    @Description("Создаёт окружение, открывает страницу логина, инициализирует UserSteps и создается новый пользователь")
    public void setUp() {
        super.setUp();
        userSteps = new UserSteps();
        driver.get(LoginPage.LOGIN_URL);
        loginResponse = userSteps.createUser(UserData.getValidUser());
    }

    @Test
    @Description("Тест проверяет вход по кнопке «Войти в аккаунт» через главную страницу")
    @DisplayName("Проверка входа по кнопке «Войти в аккаунт» на главной")
    public void testErrorsForInvalidPasswords() {
        LoginPage loginPage = new LoginPage();

        loginPage.enterEmail(driver, UserData.EMAIL);
        loginPage.enterPassword(driver, UserData.PASSWORD);
        loginPage.clickLoginButton(driver);

        String expectedUrl = MainPage.BASE_URL;

        assertEquals(expectedUrl, driver.getCurrentUrl());

    }

    @After
    @Step("Очистка данных после теста")
    @Description("Удаляет пользователя, созданный перед началом теста")
    public void tearDown() {
        super.tearDown();
        if (loginResponse != null) {
            userSteps.getAccessToken(loginResponse);
            userSteps.deleteUser();
            loginResponse = null;
        }
    }
}
