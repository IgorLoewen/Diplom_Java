package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserChoose;
import pages.MainPage;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public abstract class TestsSetUp {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private String browser;

    public TestsSetUp(String browser) {
        // Если передан браузер через параметры — используем его, иначе берем из System.getProperty
        this.browser = System.getProperty("browser", browser);
    }

    @Parameterized.Parameters(name = "Browser: {0}")
    public static Collection<Object[]> browsers() {
        return Arrays.asList(new Object[][]{
                {"chrome"},
                {"yandex"}
        });
    }

    @Before
    public void setUp() {
        driver = BrowserChoose.createDriver(browser); // Используем browser из параметров
        driver.get(MainPage.BASE_URL); // Используем Base URL как в оригинале
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
