package Model;



import static org.junit.Assert.*;

import org.junit.Test;

public class ColliderTest {

    @Test
    public void shouldUpdatePosition() {

        Collider c;

        int x;
        int y;

        int firstPositionX = x;
        int firstPositionY;

        int secondPositionX = x + 1;
        int secondPositionY = y + 1;

        x = x + 1;
        y = y + 1;

        c.updatePosition(secondPositionX, secondPositionY);


        assertTrue(x == secondPositionX);
        assertTrue(y == secondPositionY);


    }

    @Test
    public void updateSize() {
    }

    @Test
    public void isColliding() {
    }
}