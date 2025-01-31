package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {

    // ========================= Константы =========================

    // ========================= URL =========================
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    // ========================= Локаторы =========================
    private static final By LOGIN_BUTTON = By.xpath("//button[contains(@class, 'button_button__33qZ0') and text()='Войти в аккаунт']");
    private static final By PERSONAL_ACCOUNT_LINK = By.xpath("//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Личный Кабинет']");
    public static final By BUNS_TAB = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Булки']]");
    public static final By SAUCES_TAB = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Соусы']]");
    public static final By FILLINGS_TAB = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Начинки']]");
    private static final By TABS = By.className("tab_tab__1SPyG");

    // ========================= Атрибуты =========================
    private static final String ACTIVE_TAB_CLASS = "tab_tab_type_current__2BEPc";
    private static final String ATTRIBUTE_CLASS = "class"; // Вынесено для удобства изменений

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

    /**
     * Проверяет, что активен только один таб и он соответствует ожидаемому индексу.
     *
     * @param expectedIndex Ожидаемый индекс активного таба (0 — Булки, 1 — Соусы, 2 — Начинки)
     * @return true, если активен только ожидаемый таб, false в противном случае
     */
    public static boolean isCorrectTabActive(WebDriver driver, int expectedIndex) {
        List<WebElement> tabs = driver.findElements(TABS);

        for (int i = 0; i < tabs.size(); i++) {
            boolean isActive = tabs.get(i).getAttribute(ATTRIBUTE_CLASS).contains(ACTIVE_TAB_CLASS);

            if (i == expectedIndex) {
                if (!isActive) {
                    return false;
                }
            } else {
                if (isActive) {
                    return false;
                }
            }
        }
        return true;
    }
}
