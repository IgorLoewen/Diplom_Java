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

@Epic("User Login")
public class LoginTest extends TestsSetUp {

    private UserSteps userSteps;
    private Response loginResponse;
    private String email;
    private String password;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;


    @Before
    @Step("Preparing the test environment")
    @Description("Sets up the environment, opens the login page, initializes UserSteps, and creates a new user")
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
    @Description("The test verifies login via the 'Log in to account' button on the main page and also checks the login process")
    @DisplayName("Login verification via the 'Log in to account' button on the main page and login check")
    public void testLoginFromEnterToAccountButton() {
        mainPage.clickLoginButton();

        enterEmailPasswordAndClickLoginButton();

        String expectedUrl = MainPage.BASE_URL;
        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    @Description("The test verifies login via the 'Personal Account' button on the main page and immediately checks the transition from the main page to the account")
    @DisplayName("Login verification via the 'Personal Account' button and login check")
    public void testLoginWithPersonalCabinetButton() {
        mainPage.clickToLoginFromPersonalAccount();

        enterEmailPasswordAndClickLoginButton();

        String expectedUrl = MainPage.BASE_URL;
        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    @Description("The test verifies successful login via the button in the registration form")
    @DisplayName("Login verification via registration and login check")
    public void testLoginThroughRegistrationButton() {

        mainPage.clickLoginButton();
        loginPage.clickRegisterButton();
        registerPage.clickEnterButton();
        enterEmailPasswordAndClickLoginButton();

        String expectedUrl = MainPage.BASE_URL;
        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    @Description("The test verifies successful login via the button in the password recovery form. The scenario includes checking the button functionality, navigation, and credential input.")
    @DisplayName("Login verification via the button in the password recovery form and login check")
    public void testLoginThroughRecoveryPasswordTemplate() {
        mainPage.clickLoginButton();
        loginPage.clickRecoveryPasswordButton();
        registerPage.clickEnterButton();
        enterEmailPasswordAndClickLoginButton();

        String expectedUrl = MainPage.BASE_URL;
        assertEquals(expectedUrl , driver.getCurrentUrl());
    }

    @After
    @Step("Clearing data after the test")
    @Description("Deletes the user created before the test")
    public void tearDown() {
        super.tearDown();
        if (loginResponse != null) {
            userSteps.getAccessToken(loginResponse);
            userSteps.deleteUser();
        }
    }

    @Step("Entering email and password, then clicking the 'Login' button")
    private void enterEmailPasswordAndClickLoginButton() {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }
}
