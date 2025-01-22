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
import pages.MainPage;
import pages.ProfilePage;
import steps.UserSteps;

import static org.junit.Assert.assertEquals;


@Epic("Навигация авторизированного пользователя по сайту")
@RunWith(Parameterized.class)
public class NaviTest extends TestsSetUp {

    public UserSteps userSteps;
    public Response loginResponse;

    public NaviTest(String browser) {
        super(browser);
    }

    @Before
    @Step("Инициализация тестового окружения, создание пользователя и установка токена")
    @Description("Выполняет инициализацию окружения для теста: создаёт уникального пользователя через API, авторизует его, извлекает accessToken, передаёт токен в localStorage браузера и обновляет сессию для тестов.")
    public void setUp() {
        super.setUp();
        userSteps = new UserSteps();
        driver.get(MainPage.BASE_URL);
        userSteps.createUser(UserData.getValidUser());
        loginResponse = userSteps.loginUser(UserData.getValidLogin());
        userSteps.getAccessToken(loginResponse);
        userSteps.setTokenInLocalStorage(driver, userSteps.accessToken);
        driver.navigate().refresh();
    }


    @Test
    @Description("Тест проверяет, что по клику на кнопку «Личный кабинет» осуществляется переход в личный кабинет")
    @DisplayName("Переход в личный кабинет через кнопку «Личный кабинет» для авторизованного пользователя")
    public void testNavigateToPersonalCabinet() {

        MainPage mainPage = new MainPage();

        mainPage.clickToPersonalAccountFromMainPage(driver);

        String expectedUrl = ProfilePage.PROFILE_URL;
        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    @Description("Тест проверяет, что по клику на кнопку «Конструктор» осуществляется переход из личного кабинета в конструктор")
    @DisplayName("Переход из личного кабинета в конструктор через кнопку «Конструктор»")
    public void testNavigateToConstructorFromPersonalCabinet() {

        ProfilePage profilePage = new ProfilePage();
        MainPage mainPage = new MainPage();

        mainPage.clickToPersonalAccountFromMainPage(driver);
        profilePage.clickToConstructorButton(driver);

        String expectedUrl = MainPage.BASE_URL;
        assertEquals(expectedUrl, driver.getCurrentUrl());
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
}
