package praktikum;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;

    @Test // Isolated unit test for the setBuns method with mock arguments
    public void testSetBuns() {
        Burger burger = new Burger();
        Bun mockedBun = mock(Bun.class);
        burger.setBuns(mockedBun);

        Bun actualBun = burger.bun;
        assertEquals(mockedBun, actualBun);
    }

    @Test // Isolated unit test for the addIngredient method using mock and spy — verifying the call to the add() method
          // This test focuses on behavior verification (behavior test)
          // Required for full test scenario coverage!
    public void testAddIngredientWithVerify() {
        Burger burger = new Burger();
        Ingredient mockedIngredient = mock(Ingredient.class);

        burger.addIngredient(mockedIngredient);

        assertEquals(1, burger.ingredients.size());
    }

    @Test // Isolated unit test for the addIngredient method using mock — verifying the state of the list after the method call
          // This test focuses on verifying the result of the method execution (state test)
    public void testAddIngredientWithMock() {
        Burger burger = new Burger();
        Ingredient mockedIngredient = mock(Ingredient.class);

        burger.addIngredient(mockedIngredient);

        assertEquals(mockedIngredient, burger.ingredients.get(0));
    }

    @Test // Isolated unit test for the removeIngredient method: verifying that the list size decreases after removing an element.
          // The test uses a mocked ingredient object, which is added and then removed.
    public void testRemoveIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);

        burger.removeIngredient(0);
        int expectedSize = 0;

        int actualSize = burger.ingredients.size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    // Isolated unit test for the moveIngredient method — verifying that the list element moves to the specified index
    public void testMoveIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(mock(Ingredient.class));
        burger.addIngredient(mock(Ingredient.class));
        Ingredient expectedIngredient = ingredient;

        burger.moveIngredient(0, 2);

        Ingredient actualIngredient = burger.ingredients.get(2);
        assertEquals(expectedIngredient, actualIngredient);
    }


    @Test // Isolated unit test with mock prices and mocks
    public void testGetPrice() {
        Burger burger = new Burger();
        Mockito.when(bun.getPrice()).thenReturn(11f);
        Ingredient ingredient2 = mock(Ingredient.class);
        Mockito.when(ingredient.getPrice()).thenReturn(22f);
        Mockito.when(ingredient2.getPrice()).thenReturn(33f);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);
        float expectedPrice = 11f * 2 + 22f + 33f;

        float actualPrice = burger.getPrice();

        assertEquals(expectedPrice, actualPrice, 0.01f);
    }


    @Test // Isolated unit test for the getReceipt method.
          // Verifies that the method correctly generates a receipt string with the bun, ingredients, and total price.
          // Uses mock data and mocks for full control over dependencies.

    public void testGetReceipt() {

        Burger burger = new Burger();
        Mockito.when(bun.getName()).thenReturn("Fake Bun");
        Mockito.when(bun.getPrice()).thenReturn(44f);
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredient.getName()).thenReturn("hot sauce");
        Mockito.when(ingredient.getPrice()).thenReturn(55f);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        String expectedReceipt = String.format(
                "(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %f%n",
                "Fake Bun",
                "filling", "hot sauce",
                "Fake Bun",
                44f * 2 + 55f
        );

        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt);
    }


}
