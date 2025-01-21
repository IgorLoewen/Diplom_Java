package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage {
    // ========================= Константы =========================

    // ========================= URL =========================
    public static final String RECOVERY_URL = MainPage.BASE_URL + "forgot-password";

    // ========================= Локаторы =========================
    private static final By EMAIL_INPUT = By.xpath("//input[@class='text input__textfield text_type_main-default' and @type='text' and @name='name']");
    private static final By RECOVERY_BUTTON = By.xpath("//button[contains(@class, 'button_button__33qZ0') and text()='Восстановить']");
    private static final By LOGIN_LINK = By.xpath("//a[@class='Auth_link__1fOlj' and text()='Войти']");


    // ========================= Методы =========================
    public void clickLoginButton(WebDriver driver) {
        driver.findElement(LOGIN_LINK).click();
    }
}
