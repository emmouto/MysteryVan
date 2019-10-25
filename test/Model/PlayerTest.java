package Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private List<Platform> platformList = new ArrayList<>();
    private Player player = new Player("s", 1, 1, 1, 1, platformList);

    @Test
    void playerShouldBeAbleToJump() {
        double g = -7;

        player.jump();

        assertEquals(g, player.getGravity());
    }

    @Test
    void isCreatureMoving() {
        Key k = new Key();
        player.setX(2);
        player.setGravity(3);

        Key.up.isDown = true;
        player.isGrounded = true;
        player.move();
        assertEquals(2, player.getX());
        assertEquals(-6.8, player.getGravity());

        Key.left.isDown = true;
        player.move();
        assertEquals(0, player.getX());

        Key.right.isDown = true;
        player.move();
        assertEquals(2, player.getX());
    }

    @Test
    void doesGravityApplyToTheCreature() {
        player.setY(3);
        player.doGravity();

        assertEquals(6, player.getY());

    }
}