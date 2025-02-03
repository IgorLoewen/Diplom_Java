package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {

    // ========================= Constants =========================
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    // ========================= Locators =========================
    private static final By BUNS_TAB = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Булки']]");
    private static final By SAUCES_TAB = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Соусы']]");
    private static final By FILLINGS_TAB = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Начинки']]");
    private static final By LOGIN_BUTTON = By.xpath("//button[contains(@class, 'button_button__33qZ0') and text()='Войти в аккаунт']");
    private static final By PERSONAL_ACCOUNT_LINK = By.xpath("//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Личный Кабинет']");
    private static final By TABS = By.className("tab_tab__1SPyG");
    private static final By CONSTRUCTOR_BUTTON = By.xpath("//p[contains(@class, 'AppHeader_header__linkText__3q_va') and contains(@class, 'ml-2') and text()='Конструктор']");
    private static final By LOGO = By.xpath("//div[contains(@class, 'AppHeader_header__logo__2D0X2')]");

    // ========================= Attributes =========================
    private static final String ACTIVE_TAB_CLASS = "tab_tab_type_current__2BEPc";
    private static final String ATTRIBUTE_CLASS = "class";

    private final WebDriver driver;
    private final WebDriverWait wait;

    // ========================= Constructor =========================
    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ========================= Methods =========================
    @Step("Opening the main page")
    public void open() {
        driver.get(BASE_URL);
    }

    @Step("Clicking the 'Login' button")
    public void clickLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
        wait.until(ExpectedConditions.urlToBe(LoginPage.LOGIN_URL));
    }

    @Step("Clicking the 'Personal Account' link to navigate to the login page")
    public void clickToLoginFromPersonalAccount() {
        driver.findElement(PERSONAL_ACCOUNT_LINK).click();
        wait.until(ExpectedConditions.urlToBe(LoginPage.LOGIN_URL));
    }

    @Step("Clicking the 'Personal Account' link from the main page to navigate to the profile")
    public void clickToPersonalAccountFromMainPage() {
        driver.findElement(PERSONAL_ACCOUNT_LINK).click();
        wait.until(ExpectedConditions.urlToBe(ProfilePage.PROFILE_URL));
    }

    @Step("Clicking the 'Buns' tab")
    public void clickBunsTab() {
        clickWithOverlayHandling(BUNS_TAB);
    }

    @Step("Clicking the 'Sauces' tab")
    public void clickSaucesTab() {
        clickWithOverlayHandling(SAUCES_TAB);
    }

    @Step("Clicking the 'Fillings' tab")
    public void clickFillingsTab() {
        clickWithOverlayHandling(FILLINGS_TAB);
    }

    @Step("Verifying that only the expected tab with index: {expectedIndex} is active")
    public boolean isCorrectTabActive(int expectedIndex) {
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

    @Step("Clicking on the element with overlay handling")
    private void clickWithOverlayHandling(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}