package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    // ========================= Constants =========================
    public static final String PROFILE_URL = MainPage.BASE_URL + "account/profile";
    public static final String LOGIN_PAGE_URL = MainPage.BASE_URL + "login";

    // ========================= Locators =========================
    private static final By CONSTRUCTOR_BUTTON = By.xpath("//p[contains(@class, 'AppHeader_header__linkText__3q_va') and contains(@class, 'ml-2') and text()='Конструктор']");
    private static final By LOGOUT_BUTTON = By.xpath("//button[contains(@class, 'Account_button__14Yp3') and text()='Выход']");
    private static final By STELLAR_BURGER_LOGO = By.xpath("//div[contains(@class, 'AppHeader_header__logo__2D0X2')]");

    private final WebDriver driver;
    private final WebDriverWait wait;

    // ========================= Constructor =========================
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ========================= Methods =========================
    @Step("Clicking the 'Constructor' button and waiting for navigation to the main page")
    public void clickToConstructorButton() {
        driver.findElement(CONSTRUCTOR_BUTTON).click();
        wait.until(ExpectedConditions.urlToBe(MainPage.BASE_URL));
    }

    @Step("Clicking the Stellar Burgers logo and waiting for navigation to the main page")
    public void clickToLogoButton() {
        driver.findElement(STELLAR_BURGER_LOGO).click();
        wait.until(ExpectedConditions.urlToBe(MainPage.BASE_URL));
    }

    @Step("Clicking the 'Logout' button and waiting for navigation to the login page")
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGOUT_BUTTON)).click();
        wait.until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
    }
}
