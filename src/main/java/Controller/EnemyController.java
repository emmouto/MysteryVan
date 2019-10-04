package Controller;

import Model.Enemy;
import Model.Map;
import Model.Player;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.physics.MovementController;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO description
 *
 * @author
 */
public class EnemyController implements IUpdateable {
    private List<Player> players;
    private List<Enemy> enemies = new ArrayList<Enemy>();
    private List<Creature> creatureList = new ArrayList<>();
    private Long lastPathUpdate;
    private Map map;

    /**
     * Constructor.
     * TODO better description
     *
     * @param players
     *      List containing the players for the game.
     */
    public EnemyController(List<Player> players){
        this.players = players;
        spawnEnemy();
        //initiatePathfinding();
        lastPathUpdate = Game.time().now();
    }

    /**
     * TODO description
     */
    public void spawnEnemy(){
        enemies.add(new Enemy("enemy", 0, 0, 32, 50));
        Creature c = new Creature();
        creatureList.add(c);
        creatureList.get(creatureList.size()-1).setSpritePrefix(enemies.get(enemies.size()-1).getSprite());
        creatureList.get(creatureList.size()-1).getHitPoints().setMaxValue(10);
        creatureList.get(creatureList.size()-1).getHitPoints().setToMaxValue();
        creatureList.get(creatureList.size()-1).getHitBox().getBounds().height=enemies.get(enemies.size()-1).getHeight();
        creatureList.get(creatureList.size()-1).getHitBox().getBounds().width=enemies.get(enemies.size()-1).getWidth();
        creatureList.get(creatureList.size()-1).setAcceleration(50);
    }

    /**
     * TODO description
     */
    @Override
    public void update() {
        for(int i = 0; i < this.getEnemies().size(); i++){
            this.getEnemies().get(i).update();
            if (this.getEnemies().get(i).checkPlayerCollision(getPlayers().get(0))){
                System.out.println("haha");
            }
            this.getEnemies().get(i).checkGrounded(this.map.getPlatforms());
            creatureList.get(i).setLocation(enemies.get(i).getX(),enemies.get(i).getY());
        }

    }

    private void initiatePathfinding(){

    }

    private void initiateEnemies(){
        for (Creature e: creatureList){
            e.addController(new MovementController<Creature>(e));

        }
    }

    /**
     * TODO description
     *
     * @return
     *      TODO description
     */
    public List<Creature> getCreatures() {
        return creatureList;
    }

    /**
     * TODO description
     *
     * @param map
     *      TODO description
     */
    public void loadMap(Map map){
        this.map = map;
    }

    // Getters and setters - javaDoc not needed
    public List<Enemy> getEnemies() {
        return this.enemies;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }
}
