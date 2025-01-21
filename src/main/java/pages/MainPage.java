package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    // ========================= Константы =========================

    // ========================= URL =========================
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    // ========================= Локаторы =========================
    private static final By LOGIN_BUTTON = By.xpath("//button[contains(@class, 'button_button__33qZ0') and text()='Войти в аккаунт']");
    private static final By PERSONAL_ACCOUNT_LINK = By.xpath("//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Личный Кабинет']");


    // ========================= Методы =========================
    public void clickLoginButton(WebDriver driver) {
        driver.findElement(LOGIN_BUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(LoginPage.LOGIN_URL));
    }

    public void clickToLoginFromPersonalAccount(WebDriver driver) {
        driver.findElement(PERSONAL_ACCOUNT_LINK).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(LoginPage.LOGIN_URL));
    }
}
