package praktikum;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class BurgerTest {

    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;

    @Test // изолированный юнит тест метода setBuns c фиктивными аргументами
    public void testSetBuns() {
        Burger burger = new Burger();
        Bun testBun = new Bun("Fake bun", 777);
        Bun expectedBun = testBun;

        burger.setBuns(testBun);
        Bun actualBun = burger.bun;

        assertEquals(expectedBun, actualBun);
    }

    @Test // Изолированный юнит-тест метода addIngredient с помощью mock и spy — проверяем вызов метода add()
    // Этот тест фокусируется на проверке взаимодействия (behavior test)
    // Нужен для полного покрытия функциональности сценария теста!
    public void testAddIngredientWithVerify() {
        Burger burger = new Burger();
        burger.ingredients = Mockito.spy(new ArrayList<>());
        Ingredient expectedIngredient = ingredient;

        burger.addIngredient(expectedIngredient);

        Mockito.verify(burger.ingredients).add(expectedIngredient);
    }

    @Test// Изолированный юнит-тест метода addIngredient с помощью mock — проверяем состояние списка после вызова
    // Этот тест фокусируется на проверке результата выполнения метода (state test)
    public void testAddIngredientWithMock() {
        Burger burger = new Burger();
        Ingredient expectedIngredient = ingredient;

        burger.addIngredient(expectedIngredient);
        Ingredient actualIngredient = burger.ingredients.get(0);

        assertEquals(expectedIngredient, actualIngredient);
    }

    @Test // Изолированный юнит тест метода removeIngredient — проверяем состояние списка после удаления
    public void testRemoveIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);

        burger.removeIngredient(0);

        int expectedSize = 0;
        int actualSize = burger.ingredients.size();

        assertEquals(expectedSize, actualSize);
    }








}
