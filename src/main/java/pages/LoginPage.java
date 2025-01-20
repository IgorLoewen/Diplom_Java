package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    // Конкатенация BASE_URL с относительным путем
    public static final String LOGIN_URL = MainPage.BASE_URL + "login";

    // Локаторы элементов
    private static final By EMAIL_INPUT = By.id("email"); // Замените на актуальный локатор
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");

    // Методы взаимодействия
    public void enterEmail(WebDriver driver, String email) {
        driver.findElement(EMAIL_INPUT).sendKeys(email);
    }

    public void enterPassword(WebDriver driver, String password) {
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    public void clickLoginButton(WebDriver driver) {
        driver.findElement(LOGIN_BUTTON).click();
    }
}
