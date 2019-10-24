package Controller;

import Model.Enemy;
import Model.EnemyFactory;
import Model.GameLoop;
import Model.Map;
import Model.Player;

import View.GameManager;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.physics.MovementController;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for controlling the <code>Enemy</code> entities in the game.
 *
 * @author Adam Rohdell
 * @author Jonathan Carbol
 * @version 0.1
 */
public class EnemyController implements IUpdateable {
    private List<Player> players;
    private List<Enemy> enemies = new ArrayList<>();
    private List<Creature> creatureList = new ArrayList<>();
    private Long lastPathUpdate;
    private Map map;
    private EnemyFactory factory;

    /**
     * Creates a new instance of an <code>EnemyController</code>.
     *
     * @param players list containing the players for the game.
     */
    public EnemyController(List<Player> players, Map map) {
        this.players = players;
        this.map = map;
        spawnEnemy();
        //initiatePathfinding();
        lastPathUpdate = Game.time().now();
    }

    List<Creature> getCreatures() {
        return creatureList;
    }

    List<Enemy> getEnemies() {
        return this.enemies;
    }

    private List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Spawns an <code>Enemy<code> at the specified location, and sets all its properties.
     */
    private void spawnEnemy() {
        Enemy e = EnemyFactory.spawnEnemy();
        e.setTarget(this.getPlayers().get(0));
        e.setPlatforms(map.getPlatforms());
        this.getEnemies().add(e);
        Creature c = new Creature();
        creatureList.add(c);
        creatureList.get(creatureList.size() - 1).setSpritePrefix(enemies.get(enemies.size() - 1).getSprite());
        creatureList.get(creatureList.size() - 1).getHitPoints().setMaxValue(10);
        creatureList.get(creatureList.size() - 1).getHitPoints().setToMaxValue();
        creatureList.get(creatureList.size() - 1).getHitBox().getBounds().height = enemies.get(enemies.size() - 1).getHeight();
        creatureList.get(creatureList.size() - 1).getHitBox().getBounds().width = enemies.get(enemies.size() - 1).getWidth();
        creatureList.get(creatureList.size() - 1).setAcceleration(50);
        GameLoop.getInstance().setEnemies(enemies);
    }

    /**
     * ...
     */
    @Override
    public void update() {
        if(GameManager.getState() == GameManager.GameState.INGAME) {
            for (int i = 0; i < this.getEnemies().size(); i++) {
                creatureList.get(i).setLocation(enemies.get(i).getX(), enemies.get(i).getY());
                if(enemies.get(i).getHP()<0){
                    enemies.remove(i);
                    creatureList.remove(i);
                }
            }
            if (GameLoop.getInstance().checkIfDelayDone()){
                spawnEnemy();
            }
        }
    }
}
