package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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


    public static By BUNS_TAB = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and contains(@class, 'noselect')]");
    public static By SAUCES_TAB = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and contains(@class, 'tab_tab_type_current__2BEPc') and contains(@class, 'noselect')]");
    public static By FILLINGS_TAB = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Начинки']]");


    public static By BUNS_HEADER = By.xpath("//h2[contains(text(), 'Булки')]");
    public static final String EXPECTED_BUNS_HEADER_TEXT = "Булки";

    public static By SAUCES_HEADER = By.xpath("//h2[contains(text(), 'Соусы')]");
    public static final String EXPECTED_SAUCES_HEADER_TEXT = "Соусы";

    public static By FILLINGS_HEADER = By.xpath("//span[contains(@class, 'text') and contains(@class, 'text_type_main-default') and text()='Начинки']");
    public static final String EXPECTED_FILLINGS_HEADER_TEXT = "Начинки";



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

    public void clickToPersonalAccountFromMainPage(WebDriver driver) {
        driver.findElement(PERSONAL_ACCOUNT_LINK).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(ProfilePage.PROFILE_URL));
    }


    public static void clickWithOverlayHandling(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}
