package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    // ========================= Константы =========================

    // ========================= URL =========================
    public static final String LOGIN_URL = MainPage.BASE_URL + "login";

    // ========================= Локаторы =========================
    private static final By EMAIL_INPUT = By.xpath("//input[@class='text input__textfield text_type_main-default' and @type='text' and @name='name']");
    private static final By PASSWORD_INPUT = By.xpath("//input[@class='text input__textfield text_type_main-default' and @type='password' and @name='Пароль']");
    private static final By LOGIN_BUTTON = By.xpath("//button[contains(@class, 'button_button__33qZ0') and text()='Войти']");
    private static final By FORGOT_PASSWORD_BUTTON = By.xpath("//a[@class='Auth_link__1fOlj' and text()='Восстановить пароль']");

    // ========================= Методы =========================
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
