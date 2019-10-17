package Model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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