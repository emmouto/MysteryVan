package Model;

import Model.Enemies.Potato;
import Model.Maps.MapEasy;
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
    void isPlayerMoving() {
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
    void doesGravityApplyToThePlayer() {
        player.setY(3);
        player.doGravity();

        assertEquals(6, player.getY());
    }

    @Test
    void shouldTestGroundCollision(){
        Map map = new MapEasy();
        Player p = new Player("test",30,91,20,20, map.getPlatforms());
        p.setDirection(Player.Direction.RIGHT);
        p.doGravity();
        p.doGravity();
        p.update();
        assertEquals(97, p.getY());
    }

    @Test
    void shouldUpdateCollisionBox(){
        Map map = new MapEasy();
        Player p = new Player("test",30,90,20,20, map.getPlatforms());
        assertEquals(30, p.getCollider().getX());
        assertEquals(90, p.getCollider().getY());
        p.setX(10);
        p.setY(10);
        p.update();
        assertEquals(10, p.getCollider().getX());
        assertEquals(10, p.getCollider().getY());
    }

    @Test
    void shouldDamageEnemy() throws InterruptedException {
        Map map = new MapEasy();
        Player p = new Player("test",0,0,20,20, map.getPlatforms());
        p.setWeapon(new Weapon("testWeapon", 10, 100));
        p.setStrength(10);
        List<Enemy> eList = new ArrayList<>();
        eList.add(new Potato("test",0,0,50,50,5));
        GameLoop.getInstance().setEnemies(eList);
        Key.attack.toggle();
        Thread.sleep(2000);
        p.update();
        assertTrue(eList.get(0).getHP() <= 1);
    }

}