package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    // ========================= Константы =========================
    // Конкатенация BASE_URL с относительным путем
    public static final String REGISTER_URL = MainPage.BASE_URL + "register";


    // ========================= Локаторы элементов =========================
    // Поле "Имя"
    private static final By NAME_INPUT = By.xpath("//input[@name='name' and @type='text']");
    // Поле "Email"
    private static final By EMAIL_INPUT = By.xpath("(//input[@type='text'])[2]");
    // Поле "Пароль"
    private static final By PASSWORD_INPUT = By.xpath("//input[@name='Пароль' and @type='password']");
    // Кнопка "Зарегистрироваться"
    private static final By REGISTER_BUTTON = By.xpath("//button[contains(@class, 'button_button__33qZ0') and text()='Зарегистрироваться']");

    // Методы взаимодействия
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
                .until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/login"));
    }

    public static final By PASSWORD_ERROR_TEXT = By.xpath("//p[contains(text(), 'Некорректный пароль')]");

}
