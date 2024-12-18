package praktikum;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static praktikum.IngredientType.SAUCE;


public class TestTest {

    @Test
    public void testGetName(){
        Bun bun = new Bun("Таня",777);
        assertEquals("Таня",bun.getName());
    }

    @Test
    public void testGetPrice(){
        Bun bun = new Bun("Таня",777);
        assertEquals(777,bun.getPrice());
    }

    @Test
    public void testSetBuns(){
        Bun bun = new Bun("s",2);
        Burger burger = new Burger();
        burger.setBuns(bun);
        assertEquals(bun,burger.bun);
    }

    @Test
    public void testAddIngredient(){

    }





    @Test
    public void testSingleBunWithIngredient() {
        System.out.println("=== Начало сборки ===");

        // Шаг 1: Создаём объект булочки (Bun)
        Bun bun = new Bun("Test Bun", 100);
        System.out.println("Создана булочка: " + bun.getName() + " с ценой " + bun.getPrice());

        // Шаг 2: Создаём объект ингредиента (Ingredient)
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Test Sauce", 50);
        System.out.println("Создан ингредиент: " + ingredient.getName() + " с ценой " + ingredient.getPrice());

        // Шаг 3: Создаём бургер (Burger)
        Burger burger = new Burger();
        System.out.println("Создан пустой бургер.");

        // Шаг 4: Устанавливаем булочку в бургер
        burger.setBuns(bun);
        System.out.println("Булочка добавлена в бургер: " + burger.bun.getName());

        // Шаг 5: Добавляем ингредиент в бургер
        burger.addIngredient(ingredient);
        System.out.println("Добавлен ингредиент: " + ingredient.getName());

        // Шаг 6: Подсчитываем итоговую цену бургера
        float totalPrice = burger.getPrice();
        System.out.println("=== Итоговая цена ===");
        System.out.println("Цена бургера: " + totalPrice);

        // Шаг 7: Выводим чек с помощью метода getReceipt()
        System.out.println("\n=== Итоговый рецепт ===");
        System.out.println(burger.getReceipt());
    }




}
