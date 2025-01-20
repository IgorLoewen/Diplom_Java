package praktikum.test.ui;

import data.UserData;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.MainPage;
import pages.RegisterPage;
import steps.UserSteps;


import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class RegistrationTest extends TestsSetUp {

    public UserSteps userSteps;
    public Response response;

    public RegistrationTest(String browser) {
        super(browser);
    }

    @Before
    public void setUp() {
        super.setUp();
        userSteps = new UserSteps(); // Инициализация userSteps
    }

    @Test
    public void testSuccessfulRegistration() {
        driver.get(RegisterPage.REGISTER_URL);
        RegisterPage registerPage = new RegisterPage();

        registerPage.enterName(driver, UserData.NAME);
        registerPage.enterEmail(driver, UserData.EMAIL);
        registerPage.enterPassword(driver, UserData.PASSWORD);
        registerPage.clickRegisterButton(driver);

        String expectedUrl = MainPage.BASE_URL + "login";
        assertEquals("URL после регистрации должен указывать на страницу логина.", expectedUrl, driver.getCurrentUrl());
    }







    @After
    public void cleanUpAfterUserRegistration() {
        Response loginResponse = userSteps.loginUser(UserData.getValidLogin());
        userSteps.getAccessToken(loginResponse);
        userSteps.deleteUser();
    }


}








