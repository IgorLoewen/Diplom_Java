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
import pages.ProfilePage;
import pages.RegisterPage;
import steps.UserSteps;

import static org.junit.Assert.assertEquals;

@Epic("Navigation of an authorized user on the website")
public class NaviTest extends TestsSetUp {

    private UserSteps userSteps;
    private Response loginResponse;
    private String email;
    private String password;
    private MainPage mainPage;
    private ProfilePage profilePage;
    private LoginPage loginPage;
    private RegisterPage registerPage;

    @Before
    @Step("Initializing the test environment, creating a user, and setting the token")
    @Description("Creates a unique user via API, authorizes them, extracts accessToken and refreshToken, stores tokens in the browser's localStorage, and refreshes the session for tests.")
    public void setUp() {
        super.setUp();
        userSteps = new UserSteps();
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        mainPage.open();
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

//    @Test
//    @Description("The test verifies that clicking the 'Personal Account' button navigates to the personal account")
//    @DisplayName("Navigation to the personal account via the 'Personal Account' button for an authorized user")
//    public void testNavigateToPersonalCabinet() {
//
//        mainPage.clickToPersonalAccountFromMainPage();
//
//        String expectedUrl = ProfilePage.PROFILE_URL;
//        assertEquals(expectedUrl, driver.getCurrentUrl());
//    }
//
//    @Test
//    @Description("The test verifies that clicking the 'Constructor' button navigates from the personal account to the constructor")
//    @DisplayName("Navigation from the personal account to the constructor via the 'Constructor' button for an authorized user")
//    public void testNavigateToConstructorFromPersonalCabinetByClickConstructorButton() {
//        mainPage.clickToPersonalAccountFromMainPage();
//
//        profilePage.clickToConstructorButton();
//
//        String expectedUrl = MainPage.BASE_URL;
//        assertEquals(expectedUrl, driver.getCurrentUrl());
//    }
//
//    @Test
//    @Description("The test verifies that clicking the Stellar Burgers logo navigates from the personal account to the constructor")
//    @DisplayName("Navigation from the personal account to the constructor via the Stellar Burgers logo for an authorized user")
//    public void testNavigateToConstructorFromPersonalCabinetUsingLogo() {
//        mainPage.clickToPersonalAccountFromMainPage();
//
//        profilePage.clickToLogoButton();
//
//        String expectedUrl = MainPage.BASE_URL;
//        assertEquals(expectedUrl, driver.getCurrentUrl());
//    }

    @Test
    @Description("The test verifies that clicking the 'Logout' button in the personal account logs the user out")
    @DisplayName("Logging out via the 'Logout' button")
    public void testLogoutFromPersonalCabinet() {
        mainPage.clickToPersonalAccountFromMainPage();
        profilePage.clickLogoutButton();

        String expectedText = LoginPage.EXPECTED_LOGIN_TEXT;
        String actualText = driver.findElement(RegisterPage.LOGIN_HEADER).getText();

        assertEquals("The text on the login page after logout does not match the expected value", expectedText, actualText);
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
}
