package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;


public class IngredientTest {

    @Test // Изолированный юнит-тест метода getPrice(), проверяющий, что возвращается корректная цена ингредиента
    public void testGetPrice() {
        Ingredient ingredient = new Ingredient(FILLING, "mock ingredient", 777);
        float expectedPrice = 777;

        float actualPrice = ingredient.getPrice();

        assertEquals(expectedPrice, actualPrice, 0.01f);
    }

    @Test // Изолированный юнит-тест метода getName(), проверяющий, что возвращается корректное имя ингредиента
    public void testGetName() {
        Ingredient ingredient = new Ingredient(FILLING, "mock ingredient", 777);
        String expectedName = "mock ingredient";

        String actualName = ingredient.getName();

        assertEquals(expectedName, actualName);
    }

    @Test // Изолированный юнит-тест метода getType(), проверяющий, что возвращается корректный тип ингредиента
    public void testGetType() {
        Ingredient ingredient = new Ingredient(FILLING, "mock ingredient", 777);
        IngredientType expectedType = FILLING;

        IngredientType actualType = ingredient.getType();

        assertEquals(expectedType, actualType);
    }
}
