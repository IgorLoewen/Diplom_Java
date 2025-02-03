package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static pages.RegisterPage.LOGIN_HEADER;

public class LoginPage {

    // ========================= Constants =========================
    public static final String LOGIN_URL = MainPage.BASE_URL + "login";
    public static final String EXPECTED_LOGIN_TEXT = "Вход";

    // ========================= Locators =========================
    private static final By EMAIL_INPUT = By.xpath("//input[@name='name' and @type='text']");
    private static final By PASSWORD_INPUT = By.xpath("//input[@name='Пароль' and @type='password']");
    private static final By LOGIN_BUTTON = By.xpath("//button[contains(@class, 'button_button__33qZ0') and text()='Войти']");
    private static final By REGISTER_LINK = By.xpath("//a[contains(@class, 'Auth_link__1fOlj') and @href='/register']");
    private static final By FORGOT_PASSWORD_BUTTON = By.xpath("//a[contains(@class, 'Auth_link__1fOlj') and @href='/forgot-password']");

    private final WebDriver driver;
    private final WebDriverWait wait;

    // ========================= Constructor =========================
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ========================= Methods =========================
    @Step("Entering email: {email}")
    public void enterEmail(String email) {
        driver.findElement(EMAIL_INPUT).sendKeys(email);
    }

    @Step("Entering password: {password}")
    public void enterPassword(String password) {
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    @Step("Clicking the 'Login' button")
    public void clickLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
        wait.until(ExpectedConditions.urlToBe(MainPage.BASE_URL));
    }

    @Step("Clicking the 'Register' link")
    public void clickRegisterButton() {
        driver.findElement(REGISTER_LINK).click();
    }

    @Step("Clicking the 'Reset Password' button")
    public void clickRecoveryPasswordButton() {
        driver.findElement(FORGOT_PASSWORD_BUTTON).click();
    }


}

