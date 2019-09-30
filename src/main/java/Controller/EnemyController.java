package Controller;

import Model.Enemy;
import Model.Player;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.environment.Environment;
import de.gurkenlabs.litiengine.pathfinding.Path;
import de.gurkenlabs.litiengine.pathfinding.PathFinder;
import de.gurkenlabs.litiengine.pathfinding.astar.AStarPathFinder;
import de.gurkenlabs.litiengine.physics.MovementController;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnemyController implements IUpdateable {

    private List<Creature> players;
    private List<Enemy> enemies = new ArrayList<Enemy>();
    private AStarPathFinder pathFinder;
    private Long lastPathUpdate;

    public EnemyController(List<Creature> players){
        this.players = players;
        //initiatePathfinding();
        lastPathUpdate = Game.time().now();
    }

    public void changeTarget(Enemy e){

        //välj närmasta player TODO
        e.setTarget(players.get(0));

    }

    public void spawnEnemy(){
        enemies.add(new Enemy());
    }


    private void updateEnemyTarget(){
        for (Enemy e : enemies){
            if (e.getTarget() == null){
                changeTarget(e);
            }
        }
    }

    @Override
    public void update() {
        updateEnemyTarget();
        if (Game.time().since(lastPathUpdate) >= 5) {
            updatePath();
            lastPathUpdate = Game.time().now();
        }
    }

    public List<Enemy> getEnemies() {
        return this.enemies;
    }
    public List<Creature> getPlayers() {
        return this.players;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }
    public void setPlayers(List<Creature> players) {
        this.players = players;
    }


    private void initiatePathfinding(){
        pathFinder = new AStarPathFinder(Game.world().environment().getMap());
        updatePath();
    }

    private void initiateEnemies(){
        for (Enemy e: enemies){
            e.addController(new MovementController<Enemy>(e));

        }
    }

    private void updatePath(){
        for (Enemy e: enemies){
            e.updatePath(pathFinder.findPath(e, new Point((int) players.get(0).getX(), (int) players.get(0).getY())));
        }
    }
}
