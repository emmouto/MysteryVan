package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * A loop for the game.
 *
 * @author who?
 * @version 0.1
 */
public class GameLoop extends Thread{

    private static GameLoop gameLoop = null;
    List<IUpdateable> updateables = new ArrayList<IUpdateable>();
    List<Enemy> enemies = new ArrayList<>();
    private boolean interrupted = false;



    public void run() {
        while(!interrupted) {
            this.update();
            try {
                this.sleep(18);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }

    }

    public void addUpdateables(IUpdateable u){
        updateables.add(u);
    }

    private void update(){
        for (IUpdateable u : updateables){
            u.update();
        }
    }

    public static GameLoop getInstance()
    {
        if (gameLoop == null)
            gameLoop = new GameLoop();

        return gameLoop;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }
}