package Model;

import Model.Enemies.Potato;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnemyTest {
    private List<Platform> platformList = new ArrayList<>();
    Player player = new Player("sprite", 1, 1, 1, 1, platformList);
    private Enemy enemy = new Potato("sprite", 1, 1, 1, 1, 10);

    @Test
    void doesGravityApplyToCreature() {
        enemy.setY(3);
        enemy.doGravity();
        assertEquals(6, enemy.getY());
    }

    @Test
    void doesItCheckCollisionWithAPlayer() {

    }

    @Test
    void doesItCheckIfCreatureIsGrounded() {

    }

    @Test
    void isTheCreatureMoving() {
        enemy.setX(2);
        enemy.setTarget(new Player("player",5,0,0,0, new ArrayList<Platform>()));
        enemy.move();
        assertEquals(3, enemy.getX());
    }
}