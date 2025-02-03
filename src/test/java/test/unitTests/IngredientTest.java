package test.unitTests;

import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;


public class IngredientTest {

    @Test // Isolated unit test for the getPrice() method, verifying that the correct ingredient price is returned
    public void testGetPrice() {
        Ingredient ingredient = new Ingredient(FILLING, "mock ingredient", 777);
        float expectedPrice = 777;

        float actualPrice = ingredient.getPrice();

        assertEquals(expectedPrice, actualPrice, 0.01f);
    }

    @Test // Isolated unit test for the getName() method, verifying that the correct ingredient name is returned
    public void testGetName() {
        Ingredient ingredient = new Ingredient(FILLING, "mock ingredient", 777);
        String expectedName = "mock ingredient";

        String actualName = ingredient.getName();

        assertEquals(expectedName, actualName);
    }

    @Test // Isolated unit test for the getType() method, verifying that the correct ingredient type is returned
    public void testGetType() {
        Ingredient ingredient = new Ingredient(FILLING, "mock ingredient", 777);
        IngredientType expectedType = FILLING;

        IngredientType actualType = ingredient.getType();

        assertEquals(expectedType, actualType);
    }
}
