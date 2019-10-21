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
    private int delay;
    private int currentDelay;
    private boolean delayDone = false;



    public void run() {
        while(!interrupted) {
            this.update();
            try {
                sleep(18);
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
        if (currentDelay != 0){
            currentDelay--;
        } else {
            delayDone = true;
        }
    }

    public void setDelayTimer(int ms){
        this.delay = ms;
        this.currentDelay = delay;
    }

    public boolean checkIfDelayDone(){
        if (delayDone){
            delayDone = false;
            currentDelay = delay;
            return true;
        } else{
            return false;
        }
    }
}