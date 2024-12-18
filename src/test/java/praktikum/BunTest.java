package praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    @Test // Изолированный юнит тест метода getName с фиктивными аргументами
    public void testGetName(){
        Bun bun = new Bun("Fake bun",777);
        String expectedName = "Fake bun";

        String actualName = bun.getName();

        assertEquals(expectedName,actualName);
    }

    @Test // Изолированный юнит тест метода getPrice с фиктивными аргументами
    public void testGetPrice(){
        Bun bun = new Bun("Fake bun",777);
        float expectedPrice = 777;

        float actualPrice = bun.getPrice();

        assertEquals(expectedPrice,actualPrice,0.01f);
    }
}
