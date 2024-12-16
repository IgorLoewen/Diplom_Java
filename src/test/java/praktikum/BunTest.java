package praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BunTest {


    @Test // Изолированый юнит тест для getName c фейковыми данными в аргументах
    public void testGetName(){
        Bun bun = new Bun("fake bun",777);
        assertEquals("fake bun",bun.getName());
    }

    @Test // Изолированый юнит тест для getPrice c фейковыми данными в аргументах
    public void testGetPrice(){
        Bun bun = new Bun("fake bun",777);
        assertEquals(777,bun.getPrice(),0.01f);
    }

}
