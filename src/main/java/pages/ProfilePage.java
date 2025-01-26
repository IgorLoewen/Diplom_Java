package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    // ========================= Константы =========================

    // ========================= URL =========================
    public static final String PROFILE_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    // ========================= Локаторы =========================
    private static final By CONSTRUCTOR_BUTTON = By.xpath("//p[contains(@class, 'AppHeader_header__linkText__3q_va') and contains(@class, 'ml-2') and text()='Конструктор']");

    private static final By LOGOUT_BUTTON = By.xpath("//button[contains(@class, 'Account_button__14Yp3') and text()='Выход']");

    private static final By STELLAR_BURGER_LOGO = By.xpath("//div[contains(@class, 'AppHeader_header__logo__2D0X2')]");

    // ========================= Методы =========================
    public void clickToConstructorButton(WebDriver driver) {
        driver.findElement(CONSTRUCTOR_BUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));
    }

    public void clickToLogoButton(WebDriver driver) {
        driver.findElement(STELLAR_BURGER_LOGO).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));
    }

    public void clickLogoutButton(WebDriver driver) {

        WebElement clickButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(LOGOUT_BUTTON));

        clickButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/login"));
    }
}
