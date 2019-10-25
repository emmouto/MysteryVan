package Model;

import Model.Maps.MapEasy;
import Model.Maps.MapHard;
import Model.Maps.MapMedium;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    @Test
    void shouldInitializeTheCorrectMap() {
        Map map;

        map = new MapEasy();
        assertEquals(4, map.getPlatforms().size());
        assertEquals(192, map.getPlatforms().get(2).getX());

        map = new MapMedium();
        assertEquals(6, map.getPlatforms().size());
        assertEquals(0, map.getPlatforms().get(2).getX());

        map = new MapHard();
        assertEquals(5, map.getPlatforms().size());
        assertEquals(0, map.getPlatforms().get(2).getX());
    }
}