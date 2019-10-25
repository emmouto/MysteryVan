package Model;

import Model.Enemies.Potato;
import Model.Maps.MapEasy;
import View.GameManager;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {
    private List<Platform> platformList = new ArrayList<>();
    Player player = new Player("sprite", 1, 1, 1, 1, platformList);
    private Enemy enemy = new Potato("sprite", 1, 1, 1, 1, 10);

    @Test
    void doesGravityApplyToEnemy() {
        enemy.setY(3);
        enemy.doGravity();
        assertEquals(6, enemy.getY());
    }

    @Test
    void doesItCheckCollisionWithAPlayer() {
        Map map = new MapEasy();
        Enemy e = new Potato("test", 0,0,50,50,10);
        Player p = new Player("testy", 0,3,50,50, map.getPlatforms());
        e.setPlatforms(map.getPlatforms());
        p.setHP(20);
        e.setState(Enemy.State.INGAME);
        e.setTarget(p);
        e.update();
        assertEquals(10, p.getHP());



    }

    @Test
    void shouldTestGroundCollision(){
        Map map = new MapEasy();
        Enemy e = new Potato("test",30,91,20,20,10);
        Player p = new Player("testy", 0,0,30,30, map.getPlatforms());
        e.setTarget(p);
        e.setState(Enemy.State.INGAME);
        e.setPlatforms(map.getPlatforms());
        e.doGravity();
        e.doGravity();
        e.update();
        assertEquals(97, e.getY());
    }

    @Test
    void isTheEnemyMoving() {
        enemy.setX(2);
        enemy.setTarget(new Player("player",5,0,0,0, new ArrayList<Platform>()));
        enemy.move();
        assertEquals(3, enemy.getX());
    }

    @Test
    void shouldSpawnAnEnemy(){
        Enemy e = EnemyFactory.spawnEnemy();
        assertTrue(e != null);
    }
}