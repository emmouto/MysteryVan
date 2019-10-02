package Controller;

import Model.Enemy;
import Model.Player;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.physics.MovementController;

import java.util.ArrayList;
import java.util.List;

public class EnemyController implements IUpdateable {

    private List<Player> players;
    private List<Enemy> enemies = new ArrayList<Enemy>();
    private List<Creature> creatureList = new ArrayList<>();
    private Long lastPathUpdate;

    public EnemyController(List<Player> players){
        this.players = players;
        spawnEnemy();
        //initiatePathfinding();
        lastPathUpdate = Game.time().now();
    }


    public void spawnEnemy(){
        enemies.add(new Enemy("enemy"));
        Creature c = new Creature();
        creatureList.add(c);
        creatureList.get(creatureList.size()-1).setSpritePrefix(enemies.get(enemies.size()-1).getSprite());
        creatureList.get(creatureList.size()-1).getHitPoints().setMaxValue(10);
        creatureList.get(creatureList.size()-1).getHitPoints().setToMaxValue();
        creatureList.get(creatureList.size()-1).getHitBox().getBounds().height=enemies.get(enemies.size()-1).getHeight();
        creatureList.get(creatureList.size()-1).getHitBox().getBounds().width=enemies.get(enemies.size()-1).getWidth();
        creatureList.get(creatureList.size()-1).setAcceleration(50);
    }


    @Override
    public void update() {
        for(int i = 0; i < this.getEnemies().size(); i++){
            this.getEnemies().get(i).update();
            if (this.getEnemies().get(i).checkPlayerCollision(getPlayers().get(0))){
                System.out.println("haha");
            }
            creatureList.get(i).setLocation(enemies.get(i).getX(),enemies.get(i).getY());
        }

    }

    public List<Enemy> getEnemies() {
        return this.enemies;
    }
    public List<Player> getPlayers() {
        return this.players;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }


    private void initiatePathfinding(){

    }

    private void initiateEnemies(){
        for (Creature e: creatureList){
            e.addController(new MovementController<Creature>(e));

        }
    }

    public List<Creature> getCreatures() {
        return creatureList;
    }
}
