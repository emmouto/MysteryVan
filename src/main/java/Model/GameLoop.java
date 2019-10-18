package Model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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