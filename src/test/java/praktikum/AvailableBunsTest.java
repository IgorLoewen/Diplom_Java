package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AvailableBunsTest {

    private final String expectedName;
    private final float expectedPrice;
    private final int index;

    public AvailableBunsTest(String expectedName, float expectedPrice, int index) {
        this.expectedName = expectedName;
        this.expectedPrice = expectedPrice;
        this.index = index;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"black bun", 100, 0},
                {"white bun", 200, 1},
                {"red bun", 300, 2}
        };
    }

    @Test // Изолированный юнит-тест метода availableBuns(), проверяющий имя булочки из предопределённого списка по индексу
    public void testAvailableBunName() {
        Database database = new Database();
        Bun actualBun = database.availableBuns().get(index);

        String actualName = actualBun.getName();

        assertEquals(expectedName, actualName);
    }

    @Test // Изолированный юнит-тест метода availableBuns(), проверяющий имя цену из предопределённого списка по индексу
    public void testAvailableBunPrice() {
        Database database = new Database();
        Bun actualBun = database.availableBuns().get(index);

        float actualPrice = actualBun.getPrice();

        assertEquals(expectedPrice, actualPrice, 0.01f);
    }
}
