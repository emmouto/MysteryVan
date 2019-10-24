package Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    List<Platform> ps = new ArrayList<>();
    Player p = new Player("s", 1, 1, 1, 1, ps);

    @Test
    void playerShouldBeAbleToJump() {

        double g = -7;

        p.jump();

        assertEquals(g, p.getGravity());

    }

    @Test
    void isCreatureMoving() {

        Key k = new Key();
        p.setX(2);
        p.setGravity(3);

        k.up.isDown = true;
        p.isGrounded = true;
        p.move();
        assertEquals(2, p.getX());
        assertEquals(-6.8, p.getGravity());


        k.left.isDown = true;
        p.move();
        assertEquals(0, p.getX());


        k.right.isDown = true;
        p.move();
        assertEquals(2, p.getX());


    }

    @Test
    void doesGravityApplyToTheCreature() {

        p.setY(3);
        p.doGravity();

        assertEquals(6, p.getY());

    }
}