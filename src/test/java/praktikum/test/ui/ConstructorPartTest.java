package praktikum.test.ui;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.MainPage;
import steps.UserSteps;

import static org.junit.Assert.*;

import org.openqa.selenium.By;

@Epic("Переходы в разделе конструктора")
@RunWith(Parameterized.class)
public class ConstructorPartTest extends TestsSetUp {

    public ConstructorPartTest(String browser) {
        super(browser);
    }

    @Before
    @Step("Инициализация тестового окружения")
    @Description("Выполняет инициализацию окружения для теста")
    public void setUp() {
        super.setUp();
        driver.get(MainPage.BASE_URL);
    }

    // Эти тесты можно параметризировать, так как они имеют одинаковую структуру:
    // 1. Клик по табу
    // 2. Проверка, что активен только нужный таб
    // Однако параметризация не используется, так как браузеры уже параметризованы в TestsSetUp
    // и управляются через BrowserChoose. Разделение тестов обеспечивает удобночитаемость и независимость.
    // Также, по заданию требуется использовать JUnit 4, который не поддерживает двойную параметризацию.
    // В JUnit 5 можно было бы объединить браузеры и табы в один параметризованный тест через @MethodSource,
    // но так как по заданию JUnit 4 ограничен в этом плане, оставляем отдельные тесты для лучшей читаемости и поддержки.

    @Test
    @Description("Тест проверяет, что по клику на раздел «Булки» активируется соответствующий таб, а остальные не активны")
    @DisplayName("Активация таба «Булки»")
    public void testActivateBunsTab() {

        MainPage.clickWithOverlayHandling(driver, MainPage.BUNS_TAB);

        assertTrue("Таб «Булки» не активировался корректно", MainPage.isCorrectTabActive(driver, 0));
    }

    @Test
    @Description("Тест проверяет, что по клику на раздел «Соусы» активируется соответствующий таб, а остальные не активны")
    @DisplayName("Активация таба «Соусы»")
    public void testActivateSaucesTab() {

        MainPage.clickWithOverlayHandling(driver, MainPage.SAUCES_TAB);

        assertTrue("Таб «Соусы» не активировался корректно", MainPage.isCorrectTabActive(driver, 1));
    }

    @Test
    @Description("Тест проверяет, что по клику на раздел «Начинки» активируется соответствующий таб, а остальные не активны")
    @DisplayName("Активация таба «Начинки»")
    public void testActivateFillingsTab() {

        MainPage.clickWithOverlayHandling(driver, MainPage.FILLINGS_TAB);

        assertTrue("Таб «Начинки» не активировался корректно", MainPage.isCorrectTabActive(driver, 2));
    }


    @After
    @Step("Очистка данных после теста")
    @Description("Удаляет пользователя, созданного перед началом теста")
    public void tearDown() {
        super.tearDown();
    }
}
