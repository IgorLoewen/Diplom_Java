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
import pages.RegisterPage;
import steps.UserSteps;

import static org.junit.Assert.assertEquals;

@Epic("Регистрация пользователя")
public class RegistrationTest extends TestsSetUp {

    private UserSteps userSteps;
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private String email;
    private String password;
    private String name;


    @Before
    @Step("Подготовка окружения для теста")
    @Description("Создаёт окружение, открывает страницу регистрации и инициализирует UserSteps")
    public void setUp() {
        super.setUp();
        userSteps = new UserSteps();
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        registerPage.open();
        var user = UserData.getValidUser();
        email = user.getEmail();
        password = user.getPassword();
        name = user.getName();
    }

    @Test
    @Description("Тест проверяет, что пользователь может успешно зарегистрироваться и попасть на страницу логина")
    @DisplayName("Успешная регистрация пользователя")
    public void testSuccessfulRegistration() {

        registerPage.enterName(name);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.clickRegisterButton();

        String expectedText = LoginPage.EXPECTED_LOGIN_TEXT;
        String actualText = driver.findElement(RegisterPage.LOGIN_HEADER).getText();

        assertEquals("Текст на странице логина после регистрации не совпадает с ожидаемым значением", expectedText, actualText);
    }

    @Test
    @Description("Тест проверяет сообщения об ошибке при вводе некорректного пароля")
    @DisplayName("Проверка ошибки для некорректного пароля")
    public void testErrorsForInvalidPasswords() {

        registerPage.enterName(name);
        registerPage.enterEmail(email);
        registerPage.enterPassword("12345");
        registerPage.clickRegisterButtonWithoutWait();

        String actualErrorMessage = registerPage.getPasswordErrorMessage();

        assertEquals("Некорректный пароль", actualErrorMessage);
    }

    @After
    @Step("Очистка данных после теста")
    @Description("Удаляет пользователя, созданного в ходе теста")
    public void cleanUpAfterUserRegistration() {
        Response loginResponse = userSteps.loginUser(new models.UserModel(email, password, null));
        userSteps.getAccessToken(loginResponse);

        if (userSteps.accessToken != null) {
            userSteps.deleteUser();
        }
    }
}
