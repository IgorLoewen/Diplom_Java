package praktikum;

import org.junit.Test;
import tests.TestsSetUp;
import pages.MainPage;

public class OpenMainPageTest extends TestsSetUp {

    public OpenMainPageTest(String browser) {
        super(browser);
    }

    @Test
    public void openMainPageTest() {
        System.out.println("Открываем страницу: " + MainPage.BASE_URL);
        driver.get(MainPage.BASE_URL);

        // Проверка: Убедимся, что заголовок страницы содержит ожидаемый текст
        String expectedTitle = "stellar burgers";
        String actualTitle = driver.getTitle().toLowerCase();
        System.out.println("Заголовок страницы: " + actualTitle);
        assert actualTitle.contains(expectedTitle) : "Страница не загрузилась корректно!";
    }
}
