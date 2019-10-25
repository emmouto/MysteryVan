package Model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ModelTest {

    @Test
    void shouldInitiateHat(){
        Hat h = new Hat("Test", 10,10,10);
        assertTrue(h != null);
    }

    @Test
    void shouldInitiateHighScore(){
        HighScore hs = new HighScore(100, "Test");

        assertEquals("Test", hs.getPlayer());
        assertEquals(100, hs.getHighScore());
    }

    @Test
    void shouldInitiateWeapon(){
        Weapon w = new Weapon("Test", 10, 10);

        assertEquals("Test", w.getName());
        assertEquals(10, w.getDamage());
        assertEquals(10, w.getRange());
    }


}
