package Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnemyTest {


    List<Platform> ps = new ArrayList<>();
    Player p = new Player("sprite", 1, 1, 1, 1, ps);
    Enemy e = new Enemy("sprite", 1, 1, 1, 1, ps, p);

    @Test
    void doesGravityApplyToCreature() {

        e.setY(3);
        e.doGravity();

        assertEquals(6, e.getY());

    }

/*
    @Test
    void doesItCheckCollisionWithAPlayer() {

    }

    @Test
    void doesItCheckIfCreatureIsGrounded() {

    }

 */

    @Test
    void isTheCreatureMoving() {

        e.setX(2);
        e.move();

        assertEquals(3, e.getX());

    }
}