package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

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
                {IngredientType.SAUCE, "hot sauce", 100f, 0},
                {IngredientType.SAUCE, "sour cream", 200f, 1},
                {IngredientType.SAUCE, "chili sauce", 300f, 2},
                {IngredientType.FILLING, "cutlet", 100f, 3},
                {IngredientType.FILLING, "dinosaur", 200f, 4},
                {IngredientType.FILLING, "sausage", 300f, 5}
        };
    }

    @Test
    public void testAvailableIngredientType() {
        Database database = new Database();
        Ingredient actualIngredient = database.availableIngredients().get(index);

        IngredientType actualType = actualIngredient.getType();

        assertEquals(expectedType, actualType);
    }

    @Test
    public void testAvailableIngredientName() {
        Database database = new Database();
        Ingredient actualIngredient = database.availableIngredients().get(index);

        String actualName = actualIngredient.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void testAvailableIngredientPrice() {
        Database database = new Database();
        Ingredient actualIngredient = database.availableIngredients().get(index);

        float actualPrice = actualIngredient.getPrice();
        
        assertEquals(expectedPrice, actualPrice, 0.01f);
    }
}
