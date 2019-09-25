package Controller;

import Model.Enemy;
import Model.Player;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.environment.Environment;
import de.gurkenlabs.litiengine.pathfinding.Path;
import de.gurkenlabs.litiengine.pathfinding.PathFinder;
import de.gurkenlabs.litiengine.pathfinding.astar.AStarPathFinder;
import de.gurkenlabs.litiengine.physics.MovementController;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnemyController implements IUpdateable {

    private List<Player> players;
    private List<Enemy> enemies;
    private AStarPathFinder pathFinder;
    private Map<Enemy, Path> enemyPaths = new HashMap<Enemy, Path>();

    public EnemyController(ArrayList<Player> players){
        this.players = players;
        initiatePathfinding();
    }

    public void changeTarget(Enemy e){

        //välj närmasta player TODO
        e.setTarget(players.get(0));

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
        updatePath();
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
    public void setPlayers(List<Player> players) {
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
            enemyPaths.put(e, pathFinder.findPath(e, new Point(players.get(0).getPosX(), players.get(0).getPosY())));
            MovementController<Enemy> mv = (MovementController<Enemy>)e.getMovementController();
            mv.handleMovement();
        }
    }
}
