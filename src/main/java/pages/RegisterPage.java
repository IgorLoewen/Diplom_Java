package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    // ========================= Константы =========================
    // ========================= URL =========================
    public static final String REGISTER_URL = MainPage.BASE_URL + "register";

    // ========================= Локаторы =========================
    private static final By NAME_INPUT = By.xpath("//input[@name='name' and @type='text']");
    private static final By EMAIL_INPUT = By.xpath("(//input[@type='text'])[2]");
    private static final By PASSWORD_INPUT = By.xpath("//input[@name='Пароль' and @type='password']");
    private static final By REGISTER_BUTTON = By.xpath("//button[contains(@class, 'button_button__33qZ0') and text()='Зарегистрироваться']");
    private static final By PASSWORD_ERROR_TEXT = By.cssSelector("p.input__error.text_type_main-default");
    private static final By LOGIN_LINK = By.xpath("//a[contains(@class, 'Auth_link__1fOlj') and @href='/login']");

    // ========================= Методы =========================
    public void enterName(WebDriver driver, String name) {
        driver.findElement(NAME_INPUT).sendKeys(name);
    }

    public void enterEmail(WebDriver driver, String email) {
        driver.findElement(EMAIL_INPUT).sendKeys(email);
    }

    public void enterPassword(WebDriver driver, String password) {
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    public void clickRegisterButton(WebDriver driver) {
        driver.findElement(REGISTER_BUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(LoginPage.LOGIN_URL));
    }

    public void clickRegisterButtonWithoutWait(WebDriver driver) {
        driver.findElement(REGISTER_BUTTON).click();
    }

    public String getPasswordErrorMessage(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_ERROR_TEXT))
                .getText();
    }

    public void clickEnterButton(WebDriver driver) {
        driver.findElement(LOGIN_LINK).click();
    }
}
