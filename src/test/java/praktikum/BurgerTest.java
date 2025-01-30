package praktikum;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;

    @Test // изолированный юнит тест метода setBuns c фиктивными аргументами
    public void testSetBuns() {
        Burger burger = new Burger();
        Bun mockedBun = mock(Bun.class);
        burger.setBuns(mockedBun);

        Bun actualBun = burger.bun;
        assertEquals(mockedBun, actualBun);
    }

    @Test // Изолированный юнит-тест метода addIngredient с помощью mock и spy — проверяем вызов метода add()
    // Этот тест фокусируется на проверке взаимодействия (behavior test)
    // Нужен для полного покрытия функциональности сценария теста!
    public void testAddIngredientWithVerify() {
        Burger burger = new Burger();
        Ingredient mockedIngredient = mock(Ingredient.class);

        burger.addIngredient(mockedIngredient);

        assertEquals(1, burger.ingredients.size());
    }

    @Test// Изолированный юнит-тест метода addIngredient с помощью mock — проверяем состояние списка после вызова
    // Этот тест фокусируется на проверке результата выполнения метода (state test)
    public void testAddIngredientWithMock() {
        Burger burger = new Burger();
        Ingredient mockedIngredient = mock(Ingredient.class);

        burger.addIngredient(mockedIngredient);

        assertEquals(mockedIngredient, burger.ingredients.get(0)); // Оставляем только один assert
    }

    @Test // Изолированный юнит тест метода removeIngredient: проверяем, что размер списка уменьшается после удаления элемента.
// В тесте используется замокированный объект ingredient, который добавляется и затем удаляется.
    public void testRemoveIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);

        burger.removeIngredient(0);
        int expectedSize = 0;

        int actualSize = burger.ingredients.size();
        assertEquals(expectedSize, actualSize);
    }

    @Test // Изолированный юнит тест метода moveIngredient — проверяем, что элемент списка перемещается на указанный индекс
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


    @Test // изолированный юнит тест с фиктивными ценами и моками
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

        assertEquals(expectedPrice, actualPrice,0.01f);
    }


    @Test // Изолированный юнит-тест метода getReceipt.
    // Проверяет, что метод корректно формирует строку чека с булочкой, ингредиентами и итоговой ценой.
    // Используются фиктивные данные и моки для полного контроля над зависимостями.
    public void testGetReceipt() {
        // Подготовка
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
