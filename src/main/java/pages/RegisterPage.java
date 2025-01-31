package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    public static final String REGISTER_URL = MainPage.BASE_URL + "register";
    public static final By LOGIN_HEADER = By.xpath("//h2[contains(text(), 'Вход')]");

    private static final By NAME_INPUT = By.xpath("//input[@name='name' and @type='text']");
    private static final By EMAIL_INPUT = By.xpath("(//input[@type='text'])[2]");
    private static final By PASSWORD_INPUT = By.xpath("//input[@name='Пароль' and @type='password']");
    private static final By REGISTER_BUTTON = By.xpath("//button[contains(@class, 'button_button__33qZ0') and text()='Зарегистрироваться']");
    private static final By PASSWORD_ERROR_TEXT = By.cssSelector("p.input__error.text_type_main-default");
    private static final By LOGIN_LINK = By.xpath("//a[contains(@class, 'Auth_link__1fOlj') and @href='/login']");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Открытие страницы регистрации")
    public void open() {
        driver.get(REGISTER_URL);
    }

    @Step("Ввод имени: {name}")
    public void enterName(String name) {
        driver.findElement(NAME_INPUT).sendKeys(name);
    }

    @Step("Ввод email: {email}")
    public void enterEmail(String email) {
        driver.findElement(EMAIL_INPUT).sendKeys(email);
    }

    @Step("Ввод пароля: {password}")
    public void enterPassword(String password) {
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    @Step("Клик на кнопку «Зарегистрироваться» и ожидание перехода на страницу логина")
    public void clickRegisterButton() {
        driver.findElement(REGISTER_BUTTON).click();
        wait.until(ExpectedConditions.urlToBe(LoginPage.LOGIN_URL));
    }

    @Step("Клик на кнопку «Зарегистрироваться» без ожидания перехода")
    public void clickRegisterButtonWithoutWait() {
        driver.findElement(REGISTER_BUTTON).click();
    }

    @Step("Получение сообщения об ошибке для пароля")
    public String getPasswordErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_ERROR_TEXT)).getText();
    }

    @Step("Клик на ссылку «Войти»")
    public void clickEnterButton() {
        driver.findElement(LOGIN_LINK).click();
    }
}
