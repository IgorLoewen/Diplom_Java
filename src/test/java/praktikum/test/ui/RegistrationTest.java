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

import static org.junit.Assert.assertEquals;

@Epic("Регистрация пользователя")
@RunWith(Parameterized.class)
public class RegistrationTest extends TestsSetUp {

    public UserSteps userSteps;

    public RegistrationTest(String browser) {
        super(browser);
    }

    @Before
    @Step("Подготовка окружения для теста")
    @Description("Создаёт окружение, открывает страницу регистрации и инициализирует UserSteps")
    public void setUp() {
        super.setUp();
        userSteps = new UserSteps();
        driver.get(RegisterPage.REGISTER_URL);
    }

    @Test
    @Description("Тест проверяет, что пользователь может успешно зарегистрироваться и попасть на страницу логина")
    @DisplayName("Успешная регистрация пользователя")
    public void testSuccessfulRegistration() {
        RegisterPage registerPage = new RegisterPage();

        registerPage.enterName(driver, UserData.NAME);
        registerPage.enterEmail(driver, UserData.EMAIL);
        registerPage.enterPassword(driver, UserData.PASSWORD);
        registerPage.clickRegisterButton(driver);

        String expectedText = LoginPage.EXPECTED_LOGIN_TEXT;

        String actualText = driver.findElement(RegisterPage.LOGIN_HEADER).getText();

        assertEquals("Текст на странице логина после регистрации не совпадает с ожидаемым значением", expectedText, actualText);
    }


    @Test
    @Description("Тест проверяет сообщения об ошибке при вводе некорректного пароля")
    @DisplayName("Проверка ошибки для некорректного пароля")
    public void testErrorsForInvalidPasswords() {
        RegisterPage registerPage = new RegisterPage();


        registerPage.enterName(driver, UserData.NAME);
        registerPage.enterEmail(driver, UserData.EMAIL);
        registerPage.enterPassword(driver, "12345");
        registerPage.clickRegisterButtonWithoutWait(driver);

        String actualErrorMessage = registerPage.getPasswordErrorMessage(driver);
        assertEquals("Некорректный пароль", actualErrorMessage);
    }


    @After
    @Step("Очистка данных после теста")
    @Description("Удаляет пользователя, созданного в ходе теста")
    public void cleanUpAfterUserRegistration() {
        Response loginResponse = userSteps.loginUser(UserData.getValidLogin());
        userSteps.getAccessToken(loginResponse);

        if (userSteps.accessToken != null) {
            userSteps.deleteUser();
        }
    }

}
