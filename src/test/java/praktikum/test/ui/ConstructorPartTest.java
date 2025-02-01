package praktikum.test.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.MainPage;

import static org.junit.Assert.assertTrue;

@Epic("Переходы в разделе конструктора")
public class ConstructorPartTest extends TestsSetUp {

    private MainPage mainPage;

    @Before
    @Step("Инициализация тестового окружения")
    @Description("Выполняет инициализацию окружения для теста")
    public void setUp() {
        super.setUp();
        mainPage = new MainPage(driver);
        mainPage.open();
    }

    @Test
    @Description("Тест проверяет, что по клику на раздел «Соусы» активируется соответствующий таб, а остальные не активны")
    @DisplayName("Активация таба «Соусы»")
    public void testActivateSaucesTab() {

        mainPage.clickSaucesTab();

        assertTrue("Таб «Соусы» не активировался корректно", mainPage.isCorrectTabActive(1));
    }


    @Test
    @Description("Тест проверяет, что по клику на раздел «Начинки» активируется соответствующий таб, а остальные не активны")
    @DisplayName("Активация таба «Начинки»")
    public void testActivateFillingsTab() {

        mainPage.clickFillingsTab();

        assertTrue("Таб «Начинки» не активировался корректно", mainPage.isCorrectTabActive(2));
    }

    @Test
    @Description("Тест проверяет, что по клику на раздел «Булки» активируется соответствующий таб, а остальные не активны")
    @DisplayName("Активация таба «Булки»")
    public void testActivateBunsTab() {

        mainPage.clickBunsTab();

        assertTrue("Таб «Булки» не активировался корректно", mainPage.isCorrectTabActive(0));
    }


    @After
    @Step("Очистка данных после теста")
    @Description("Закрываем браузер")
    public void tearDown() {
        super.tearDown();
    }

}
