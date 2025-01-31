package praktikum.test.ui;

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
    @Description("Настройка драйвера и базового URL для тестов")
    @DisplayName("Настройка тестового окружения")
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        driver = BrowserChoose.createDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    @Description("Закрытие браузера после выполнения тестов")
    @DisplayName("Завершение тестового окружения")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
