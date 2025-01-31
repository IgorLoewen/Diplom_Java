package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    // ========================= Константы =========================

    // ========================= URL =========================
    public static final String PROFILE_URL = "https://stellarburgers.nomoreparties.site/account/profile";
    public static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";
    public static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    // ========================= Локаторы =========================
    private static final By CONSTRUCTOR_BUTTON = By.xpath("//p[contains(@class, 'AppHeader_header__linkText__3q_va') and contains(@class, 'ml-2') and text()='Конструктор']");
    private static final By LOGOUT_BUTTON = By.xpath("//button[contains(@class, 'Account_button__14Yp3') and text()='Выход']");
    private static final By STELLAR_BURGER_LOGO = By.xpath("//div[contains(@class, 'AppHeader_header__logo__2D0X2')]");

    // ========================= Атрибуты =========================
    private static final Duration TIMEOUT = Duration.ofSeconds(10); // Время ожидания

    // ========================= Методы =========================
    @Step("Клик на кнопку «Конструктор» и ожидание перехода на главную страницу")
    public void clickToConstructorButton(WebDriver driver) {
        driver.findElement(CONSTRUCTOR_BUTTON).click();
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.urlToBe(MAIN_PAGE_URL));
    }

    @Step("Клик на логотип Stellar Burgers и ожидание перехода на главную страницу")
    public void clickToLogoButton(WebDriver driver) {
        driver.findElement(STELLAR_BURGER_LOGO).click();
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.urlToBe(MAIN_PAGE_URL));
    }

    @Step("Клик на кнопку «Выйти» и ожидание перехода на страницу логина")
    public void clickLogoutButton(WebDriver driver) {
        WebElement clickButton = new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(LOGOUT_BUTTON));

        clickButton.click();

        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
    }
}
