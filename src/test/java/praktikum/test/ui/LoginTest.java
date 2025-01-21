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
import pages.RegisterPage;
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
        driver.get(MainPage.BASE_URL);
        loginResponse = userSteps.createUser(UserData.getValidUser());
    }

    @Test
    @Description("Тест проверяет вход по кнопке «Войти в аккаунт» через главную страницу")
    @DisplayName("Проверка входа по кнопке «Войти в аккаунт» на главной")
    public void testLoginFromEnterToAccountButton() {
        LoginPage loginPage = new LoginPage();
        MainPage mainPage = new MainPage();

        mainPage.clickLoginButton(driver);
        loginPage.enterEmail(driver, UserData.EMAIL);
        loginPage.enterPassword(driver, UserData.PASSWORD);
        loginPage.clickLoginButton(driver);

        String expectedUrl = MainPage.BASE_URL;
        assertEquals(expectedUrl, driver.getCurrentUrl());

    }

    @Test
    @Description("Тест проверяет вход через кнопку «Личный кабинет» на главной странице и сразу проверяет переход с главной страницы в кабинет")
    @DisplayName("Проверка входа через кнопку «Личный кабинет»")
    public void testLoginWithPersonalCabinetButton() {
        LoginPage loginPage = new LoginPage();
        MainPage mainPage = new MainPage();

        mainPage.clickToLoginFromPersonalAccount(driver);
        loginPage.enterEmail(driver, UserData.EMAIL);
        loginPage.enterPassword(driver, UserData.PASSWORD);
        loginPage.clickLoginButton(driver);

        String expectedUrl = MainPage.BASE_URL;
        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    @Description("Тест проверяет успешность входа через кнопку в форме регистрации")
    @DisplayName("Проверка входа через регистрацию")
    public void testLoginThroughRegistrationButton() {
        RegisterPage registerPage = new RegisterPage();
        MainPage mainPage = new MainPage();
        LoginPage loginPage = new LoginPage();

        mainPage.clickLoginButton(driver);
        loginPage.clickRegisterButton(driver);
        registerPage.clickEnterButton(driver);
        loginPage.enterEmail(driver, UserData.EMAIL);
        loginPage.enterPassword(driver, UserData.PASSWORD);
        loginPage.clickLoginButton(driver);

        String expectedUrl = MainPage.BASE_URL;
        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    @Description("Тест проверяет успешность входа в систему через кнопку, расположенную в форме восстановления пароля. Сценарий включает проверку корректности работы кнопки, переходов и ввода учетных данных")
    @DisplayName("Проверка входа через кнопку в форме восстановления пароля")
    public void testLoginThroughRecoveryPasswortTemplate() {
        RegisterPage registerPage = new RegisterPage();
        MainPage mainPage = new MainPage();
        LoginPage loginPage = new LoginPage();

        mainPage.clickLoginButton(driver);
        loginPage.clickRecoveryPasswordButton(driver);
        registerPage.clickEnterButton(driver);
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
