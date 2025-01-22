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
public class NaviTest extends TestsSetUp {

    public UserSteps userSteps;
    public Response loginResponse;

    public NaviTest(String browser) {
        super(browser);
    }

    @Before
    @Step("Подготовка окружения для теста")
    @Description("Создаёт окружение, открывает страницу логина, инициализирует UserSteps и создается новый пользователь")
    public void setUp() {
        super.setUp();
        userSteps = new UserSteps();
        driver.get(MainPage.BASE_URL);
        userSteps.createUser(UserData.getValidUser());


    }

    @Test
    @Description("Тест проверяет, что по клику на кнопку «Личный кабинет» осуществляется переход в личный кабинет")
    @DisplayName("Переход в личный кабинет через кнопку «Личный кабинет» для зарегистрированного пользователя")
    public void testNavigateToPersonalCabinet() {
        LoginPage loginPage = new LoginPage();
        MainPage mainPage = new MainPage();

        mainPage.clickToLoginFromPersonalAccount(driver);
        loginPage.enterEmail(driver, UserData.EMAIL);
        loginPage.enterPassword(driver, UserData.PASSWORD);
        loginPage.clickLoginButton(driver);
        mainPage.clickToPersonalAccountFromMainPage(driver);

        String expectedUrl = MainPage.PROFILE_URL;
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
            loginResponse = null;
        }
    }
}
