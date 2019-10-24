package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void shouldInitializeTheCorrectMap() {

        String map1 = "map1";
        String map2 = "map2";
        String map3 = "map3";


        Map m;
        m = new Map(map1);
        assertEquals(5, m.getPlatforms().size());
        assertEquals(0, m.getPlatforms().get(2).getX());

        m = new Map(map2);
        assertEquals(4, m.getPlatforms().size());
        assertEquals(192, m.getPlatforms().get(2).getX());

        m = new Map(map3);
        assertEquals(6, m.getPlatforms().size());
        assertEquals(0, m.getPlatforms().get(2).getX());

    }
}