package praktikum.test.ui;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserChoose;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;


public abstract class TestsSetUp {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private final String browser;

    public TestsSetUp(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters(name = "Browser: {0}")
    public static Collection<Object[]> browsers() {
        return Arrays.asList(new Object[][]{
                {"chrome"},
                {"chrome"}
        });
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        driver = BrowserChoose.createDriver(browser);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
