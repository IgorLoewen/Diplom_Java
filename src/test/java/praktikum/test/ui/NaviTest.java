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
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;
import pages.RegisterPage;
import steps.UserSteps;

import static org.junit.Assert.assertEquals;


@Epic("Навигация авторизированного пользователя по сайту")
@RunWith(Parameterized.class)
public class NaviTest extends TestsSetUp {

    public UserSteps userSteps;
    public Response loginResponse;
    private String email;
    private String password;

    public NaviTest(String browser) {
        super(browser);
    }

    @Before
    @Step("Инициализация тестового окружения, создание пользователя и установка токена")
    @Description("Выполняет инициализацию окружения для теста: создаёт уникального пользователя через API, авторизует его, извлекает accessToken и refreshToken, передаёт токены в localStorage браузера и обновляет сессию для тестов.")
    public void setUp() {
        super.setUp();
        userSteps = new UserSteps();
        driver.get(MainPage.BASE_URL);
        var user = UserData.getValidUser();
        email = user.getEmail();
        password = user.getPassword();
        userSteps.createUser(user);
        loginResponse = userSteps.loginUser(user);
        userSteps.getAccessToken(loginResponse);
        userSteps.setTokenInLocalStorage(driver, userSteps.accessToken);
        userSteps.setRefreshTokenInLocalStorage(driver, userSteps.refreshToken);
        driver.navigate().refresh();

    }


    @Test
    @Description("Тест проверяет, что по клику на кнопку «Личный кабинет» осуществляется переход в личный кабинет")
    @DisplayName("Переход в личный кабинет через кнопку «Личный кабинет» для авторизованного пользователя")
    public void testNavigateToPersonalCabinet() {

        navigateToPersonalCabinet(driver);

        String expectedUrl = ProfilePage.PROFILE_URL;
        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    @Description("Тест проверяет, что по клику на кнопку «Конструктор» осуществляется переход из личного кабинета в конструктор")
    @DisplayName("Переход из личного кабинета в конструктор через кнопку «Конструктор» для авторизованного пользователя")
    public void testNavigateToConstructorFromPersonalCabinetByClickConstructorButton() {

        ProfilePage profilePage = new ProfilePage();
        navigateToPersonalCabinet(driver);

        profilePage.clickToConstructorButton(driver);

        String expectedUrl = MainPage.BASE_URL;
        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    @Description("Тест проверяет, что по клику на логотип Stellar Burgers осуществляется переход из личного кабинета в конструктор")
    @DisplayName("Переход из личного кабинета в конструктор через логотип Stellar Burgers для авторизованного пользователя")
    public void testNavigateToConstructorFromPersonalCabinetUsingLogo() {

        ProfilePage profilePage = new ProfilePage();
        navigateToPersonalCabinet(driver);

        profilePage.clickToLogoButton(driver);

        String expectedUrl = MainPage.BASE_URL;
        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    @Description("Тест проверяет, что по кнопке «Выйти» в личном кабинете осуществляется выход из аккаунта")
    @DisplayName("Выход из аккаунта через кнопку «Выйти»")
    public void testLogoutFromPersonalCabinet() {

        ProfilePage profilePage = new ProfilePage();
        navigateToPersonalCabinet(driver);

        profilePage.clickLogoutButton(driver);

        String expectedText = LoginPage.EXPECTED_LOGIN_TEXT;
        String actualText = driver.findElement(RegisterPage.LOGIN_HEADER).getText();

        assertEquals("Текст на странице логина после выхода не совпадает с ожидаемым значением", expectedText, actualText);
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

    @Step("Переход в личный кабинет через кнопку на главной странице")
    private void navigateToPersonalCabinet(WebDriver driver) {
        MainPage mainPage = new MainPage();
        mainPage.clickToPersonalAccountFromMainPage(driver);
    }

}

