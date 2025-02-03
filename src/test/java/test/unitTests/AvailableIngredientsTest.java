package test.unitTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Database;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AvailableIngredientsTest {

    private final IngredientType expectedType;
    private final String expectedName;
    private final float expectedPrice;
    private final int index;

    public AvailableIngredientsTest(IngredientType expectedType, String expectedName, float expectedPrice, int index) {
        this.expectedType = expectedType;
        this.expectedName = expectedName;
        this.expectedPrice = expectedPrice;
        this.index = index;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100, 0},
                {IngredientType.SAUCE, "sour cream", 200, 1},
                {IngredientType.SAUCE, "chili sauce", 300, 2},
                {IngredientType.FILLING, "cutlet", 100, 3},
                {IngredientType.FILLING, "dinosaur", 200, 4},
                {IngredientType.FILLING, "sausage", 300, 5}
        };
    }

    @Test // Isolated parameterized unit test for the availableIngredients() method,
          // verifying that the ingredient type matches the expected value
    public void testAvailableIngredientType() {
        Database database = new Database();
        Ingredient actualIngredient = database.availableIngredients().get(index);

        IngredientType actualType = actualIngredient.getType();

        assertEquals(expectedType, actualType);
    }

    @Test // Isolated parameterized unit test for the availableIngredients() method,
          // verifying that the ingredient name matches the expected value
    public void testAvailableIngredientName() {
        Database database = new Database();
        Ingredient actualIngredient = database.availableIngredients().get(index);

        String actualName = actualIngredient.getName();

        assertEquals(expectedName, actualName);
    }

    @Test // Isolated parameterized unit test for the availableIngredients() method,
          // verifying that the ingredient price matches the expected value
    public void testAvailableIngredientPrice() {
        Database database = new Database();
        Ingredient actualIngredient = database.availableIngredients().get(index);

        float actualPrice = actualIngredient.getPrice();
        
        assertEquals(expectedPrice, actualPrice, 0.01f);
    }
}
