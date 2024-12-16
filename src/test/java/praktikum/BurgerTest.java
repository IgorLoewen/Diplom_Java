package praktikum;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class BurgerTest {

    @Test // изолированный юнит тест метода setBuns c фиктивными аргументами
    public void testSetBuns() {
        Burger burger = new Burger();
        Bun testBun = new Bun("Fake bun", 777);
        burger.setBuns(testBun);
        assertEquals(testBun, burger.bun);
    }

    @Test //изолированный юнит-тест метода addIngredient с помощью spy и mock — проверяем вызов метода add()
    // Этот тест фокусируется на проверке взаимодействия (behavior test)
      //нужен для полного покрытия функциональности сценария теста!
    public void testAddIngredientWithVerify() {
        Burger burger = new Burger();
        burger.ingredients = spy(new ArrayList<>());
        Ingredient mockIngredient = Mockito.mock(Ingredient.class);
        burger.addIngredient(mockIngredient);
        verify(burger.ingredients).add(mockIngredient);
    }

    @Test // Изолированный юнит-тест метода addIngredient с помощью mock — проверяем состояние списка после вызова
    // Этот тест фокусируется на проверке результата выполнения метода (state test)
    public void testAddIngredientWithMock() {
        Burger burger = new Burger();
        burger.ingredients = new ArrayList<>();
        Ingredient mockIngredient = Mockito.mock(Ingredient.class);
        burger.addIngredient(mockIngredient);
        assertEquals(1, burger.ingredients.size());
    }





}
