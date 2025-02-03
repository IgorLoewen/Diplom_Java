package test.unitTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Database;

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

    @Test // Isolated unit test for the availableBuns() method, checking the bun name from the predefined list by index
    public void testAvailableBunName() {
        Database database = new Database();
        Bun actualBun = database.availableBuns().get(index);

        String actualName = actualBun.getName();

        assertEquals(expectedName, actualName);
    }

    @Test // Isolated unit test for the availableBuns() method, checking the bun price from the predefined list by index
    public void testAvailableBunPrice() {
        Database database = new Database();
        Bun actualBun = database.availableBuns().get(index);

        float actualPrice = actualBun.getPrice();

        assertEquals(expectedPrice, actualPrice, 0.01f);
    }
}
