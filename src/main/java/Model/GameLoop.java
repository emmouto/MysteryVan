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

    List<IUpdateable> updateables = new ArrayList<IUpdateable>();
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
}