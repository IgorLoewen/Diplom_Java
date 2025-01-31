package praktikum.test.ui;

import data.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import steps.UserSteps;

import static org.junit.Assert.assertEquals;

@Epic("Логин пользователя")
public class LoginTest extends TestsSetUp {

    private UserSteps userSteps;
    private Response loginResponse;
    private String email;
    private String password;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;


    @Before
    @Step("Подготовка окружения для теста")
    @Description("Создаёт окружение, открывает страницу логина, инициализирует UserSteps и создается новый пользователь")
    public void setUp() {
        super.setUp();
        userSteps = new UserSteps();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        mainPage.open();
        var user = UserData.getValidUser();
        email = user.getEmail();
        password = user.getPassword();
        loginResponse = userSteps.createUser(user);
    }

    @Test
    @Description("Тест проверяет вход по кнопке «Войти в аккаунт» через главную страницу")
    @DisplayName("Проверка входа по кнопке «Войти в аккаунт» на главной и так же проверка логина")
    public void testLoginFromEnterToAccountButton() {
        mainPage.clickLoginButton();

        enterEmailPasswordAndClickLoginButton();

        String expectedUrl = MainPage.BASE_URL;
        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    @Description("Тест проверяет вход через кнопку «Личный кабинет» на главной странице и сразу проверяет переход с главной страницы в кабинет")
    @DisplayName("Проверка входа через кнопку «Личный кабинет» и так же проверка логина")
    public void testLoginWithPersonalCabinetButton() {
        mainPage.clickToLoginFromPersonalAccount();

        enterEmailPasswordAndClickLoginButton();

        String expectedUrl = MainPage.BASE_URL;
        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    @Description("Тест проверяет успешность входа через кнопку в форме регистрации")
    @DisplayName("Проверка входа через регистрацию и так же проверка логина")
    public void testLoginThroughRegistrationButton() {

        mainPage.clickLoginButton();
        loginPage.clickRegisterButton();
        registerPage.clickEnterButton();
        enterEmailPasswordAndClickLoginButton();

        String expectedUrl = MainPage.BASE_URL;
        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    @Description("Тест проверяет успешность входа в систему через кнопку, расположенную в форме восстановления пароля. Сценарий включает проверку корректности работы кнопки, переходов и ввода учетных данных")
    @DisplayName("Проверка входа через кнопку в форме восстановления пароля и так же проверка логина")
    public void testLoginThroughRecoveryPasswordTemplate() {
        mainPage.clickLoginButton();
        loginPage.clickRecoveryPasswordButton();
        registerPage.clickEnterButton();
        enterEmailPasswordAndClickLoginButton();

        String expectedUrl = MainPage.BASE_URL;
        assertEquals(expectedUrl , driver.getCurrentUrl());
    }

    @After
    @Step("Очистка данных после теста")
    @Description("Удаляет пользователя, созданного перед началом теста")
    public void tearDown() {
        super.tearDown();
        if (loginResponse != null) {
            userSteps.getAccessToken(loginResponse);
            userSteps.deleteUser();
        }
    }

    @Step("Ввод email и пароля, затем клик на кнопку «Войти»")
    private void enterEmailPasswordAndClickLoginButton() {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }
}
