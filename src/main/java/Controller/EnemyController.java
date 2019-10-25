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
 * Class for controlling the Enemy entities in the game.
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
    public void spawnEnemy() {
        Enemy e = EnemyFactory.spawnEnemy();
        e.setTarget(this.getPlayers().get(0));
        e.setPlatforms(map.getPlatforms());
        this.getEnemies().add(e);
        Creature c = new Creature();
        c.setSpritePrefix(e.getSprite());
        c.getHitPoints().setMaxValue(10);
        c.getHitPoints().setToMaxValue();
        c.getHitBox().getBounds().height = e.getHeight();
        c.getHitBox().getBounds().width = e.getWidth();
        creatureList.add(c);
        Game.world().environment().add(c);
        GameLoop.getInstance().setEnemies(enemies);
    }

    /**
     * Updates the sprite locations of the enemies and spawns new enemies, when the game is running.
     */
    @Override
    public void update() {
        if(GameManager.getState() == GameManager.GameState.INGAME) {

            for (int i = 0; i < this.getEnemies().size(); i++) {
                creatureList.get(i).setLocation(enemies.get(i).getX(), enemies.get(i).getY());
                enemies.get(i).setState(Enemy.State.INGAME);
                if(enemies.get(i).getHP()<0){
                    Game.world().environment().remove(creatureList.get(i));
                    enemies.remove(i);
                    creatureList.remove(i);
                    i--;
                }
            }
            enemies=GameLoop.getInstance().getEnemies();
            /*if (GameLoop.getInstance().checkIfDelayDone() && this.getEnemies().size() < 2){
                spawnEnemy();
            }*/
        }else{
            for(int i = 0; i < this.getEnemies().size();i++){
                enemies.get(i).setState(Enemy.State.PAUSE);
            }
        }
    }
}
