package Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ColliderTest {
    @Test
    void returnUpdatedPositionsOfCollider() {
        int x = 0;
        int y = 0;

        int secPosX = 1;
        int secPosY = 1;

        x = x + 1;
        y = y + 1;


        Collider c = new Collider();
        c.updatePosition(x, y);

        assertEquals(c.getX(), secPosX);
        assertEquals(c.getY(), secPosY);
    }

    @Test
    void shouldUpdateTheSizeOfTheCollider() {
        int width = 0;
        int height = 0;

        int updWidth = 5;
        int updHeight = 5;

        width = width + 5;
        height = height + 5;

        Collider c = new Collider();
        c.updateSize(width,height);

        assertEquals(c.getWidth(), updWidth);
        assertEquals(c.getHeight(), updHeight);
    }

    @Test
    void returnsWhetherPlayerAndObjectAreColliding() {
        Collider c = new Collider();
        c.updatePosition(1,1);
        c.updateSize(1,1);
        List<Platform> px = new ArrayList<Platform>();

        Player p = new Player("sprite", 1, 1, 1, 1, px);

        assertTrue(c.isColliding(p, "UP"));
        assertTrue(c.isColliding(p, "DOWN"));
        assertTrue(c.isColliding(p, "LEFT"));
        assertTrue(c.isColliding(p, "RIGHT"));
    }
}