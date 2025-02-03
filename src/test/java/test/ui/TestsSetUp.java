package test.ui;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserChoose;

import java.time.Duration;

public abstract class TestsSetUp {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @Before
    @Description("Configuring the driver and base URL for tests")
    @DisplayName("Setting up the test environment")
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        driver = BrowserChoose.createDriver();
    }

    @After
    @Description("Closing the browser after test execution")
    @DisplayName("Shutting down the test environment")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
