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

@Epic("User Registration")
public class RegistrationTest extends TestsSetUp {

    private UserSteps userSteps;
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private String email;
    private String password;
    private String name;


    @Before
    @Step("Preparing the test environment")
    @Description("Sets up the environment, opens the registration page, and initializes UserSteps")
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
    @Description("The test verifies that a user can successfully register and be redirected to the login page")
    @DisplayName("Successful user registration")
    public void testSuccessfulRegistration() {

        registerPage.enterName(name);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.clickRegisterButton();

        String expectedText = LoginPage.EXPECTED_LOGIN_TEXT;
        String actualText = driver.findElement(RegisterPage.LOGIN_HEADER).getText();

        assertEquals("The text on the login page after registration does not match the expected value", expectedText, actualText);
    }

    @Test
    @Description("The test verifies error messages when entering an incorrect password")
    @DisplayName("Error validation for incorrect password")
    public void testErrorsForInvalidPasswords() {

        registerPage.enterName(name);
        registerPage.enterEmail(email);
        registerPage.enterPassword("12345");
        registerPage.clickRegisterButtonWithoutWait();

        String actualErrorMessage = registerPage.getPasswordErrorMessage();

        assertEquals("Некорректный пароль", actualErrorMessage);
    }

    @After
    @Step("Clearing data after the test")
    @Description("Deletes the user created during the test")
    public void cleanUpAfterUserRegistration() {
        Response loginResponse = userSteps.loginUser(new models.UserModel(email, password, null));
        userSteps.getAccessToken(loginResponse);

        if (userSteps.accessToken != null) {
            userSteps.deleteUser();
        }
    }
}
