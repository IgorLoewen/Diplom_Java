package praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    @Test // Изолированный юнит тест метода getName с фейковыми аргументами
    public void testGetName(){
        Bun bun = new Bun("Fake bun",777);
        assertEquals("Fake bun",bun.getName());
    }

    @Test // Изолированный юнит тест метода getPrice с фейковыми аргументами
    public void testGetPrice(){
        Bun bun = new Bun("Fake bun",777);
        assertEquals(777,bun.getPrice(),0.01f);
    }
}
