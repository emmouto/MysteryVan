package Model;

import Model.Maps.MapEasy;
import Model.Maps.MapHard;
import Model.Maps.MapMedium;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void shouldInitializeTheCorrectMap() {




        Map m;
        m = new MapEasy();
        assertEquals(5, m.getPlatforms().size());
        assertEquals(0, m.getPlatforms().get(2).getX());

        m = new MapMedium();
        assertEquals(4, m.getPlatforms().size());
        assertEquals(192, m.getPlatforms().get(2).getX());

        m = new MapHard();
        assertEquals(6, m.getPlatforms().size());
        assertEquals(0, m.getPlatforms().get(2).getX());

    }
}