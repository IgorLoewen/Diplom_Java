package praktikum.test.ui;

import data.UserData;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;
import pages.RegisterPage;
import steps.UserSteps;

import static org.junit.Assert.assertEquals;


@Epic("Переходы в разделе конструктора")
@RunWith(Parameterized.class)
public class ConstructorPartTest extends TestsSetUp {

    public UserSteps userSteps;

    public ConstructorPartTest(String browser) {
        super(browser);
    }

    @Before
    @Step("Инициализация тестового окружения")
    @Description("Выполняет инициализацию окружения для теста")
    public void setUp() {
        super.setUp();
        userSteps = new UserSteps();
        driver.get(MainPage.BASE_URL);
    }

    @Test
    @Description("Тест проверяет, что по клику на раздел «Булки» пользователь переходит к соответствующему разделу")
    @DisplayName("Переход к разделу «Булки»")
    public void testNavigateToBunsSection() {

        MainPage.clickWithOverlayHandling(driver, MainPage.BUNS_TAB);

        String expectedText = MainPage.EXPECTED_BUNS_HEADER_TEXT;
        String actualText = driver.findElement(MainPage.BUNS_HEADER).getText();

        assertEquals("Текст в конструкторе при переходе к разделу «Булки» не соответствует заданию", expectedText, actualText);


    }

    @Test
    @Description("Тест проверяет, что по клику на раздел «Соусы» пользователь переходит к соответствующему разделу")
    @DisplayName("Переход к разделу «Соусы»")
    public void testNavigateToSaucesSection() {

        MainPage.clickWithOverlayHandling(driver, MainPage.SAUCES_TAB);

        String expectedText = MainPage.EXPECTED_SAUCES_HEADER_TEXT;
        String actualText = driver.findElement(MainPage.SAUCES_HEADER).getText();

        assertEquals("Текст в конструкторе при переходе к разделу «Соусы» не соответствует заданию", expectedText, actualText);


    }

    @Test
    @Description("Тест проверяет, что по клику на раздел «Начинки» пользователь переходит к соответствующему разделу")
    @DisplayName("Переход к разделу «Начинки»")
    public void testNavigateToFillingsSection() {

        MainPage.clickWithOverlayHandling(driver, MainPage.FILLINGS_TAB);

        String expectedText = MainPage.EXPECTED_FILLINGS_HEADER_TEXT;
        String actualText = driver.findElement(MainPage.FILLINGS_HEADER).getText();

        assertEquals("Текст в конструкторе при переходе к разделу «Начинки» не соответствует заданию", expectedText, actualText);
    }

    @After
    @Step("Очистка данных после теста")
    @Description("Удаляет пользователя, созданного перед началом теста")
    public void tearDown() {
        super.tearDown();
    }
}

