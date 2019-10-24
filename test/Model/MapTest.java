package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void shouldInitializeTheRightMap() {

        String map1 = "map1";
        String map2 = "map2";
        String map3 = "map3";


        Map m;
        m = new Map(map1);
        assertEquals(5, m.getPlatforms().size());
        assertEquals(120, m.getPlatforms().get(2).getX());

        m = new Map(map2);
        assertTrue(m.getPlatforms().size() == 4);
        assertEquals(192, m.getPlatforms().get(2).getX());

        m = new Map(map3);
        assertTrue(m.getPlatforms().size() == 5);
        assertEquals(120, m.getPlatforms().get(2).getX());

    }
}