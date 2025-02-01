package praktikum;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BunTest {

    @Test // Isolated unit test for the getName method with mock arguments
    public void testGetName(){
        Bun bun = new Bun("Fake bun",777);
        String expectedName = "Fake bun";

        String actualName = bun.getName();

        assertEquals(expectedName,actualName);
    }

    @Test // Isolated unit test for the getPrice method with mock arguments
    public void testGetPrice(){
        Bun bun = new Bun("Fake bun",777);
        float expectedPrice = 777;

        float actualPrice = bun.getPrice();

        assertEquals(expectedPrice,actualPrice,0.01f);
    }
}
