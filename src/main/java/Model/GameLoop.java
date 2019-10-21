package Model;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
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
    private int delay;
    private int currentDelay;
    private boolean delayDone = false;



    public void run() {
        /*File file = new File("src/main/java/Model/game.json");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            if (br.readLine() != "[]") {
                while(br.readLine()!= ""){
                    updateables.add(readJSON());
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        while(!interrupted) {
            try {
                this.update();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    private void update() throws IOException {

        for (IUpdateable u : updateables){
            u.update();
            //writeJSON(u);
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

    private void writeJSON(IUpdateable iUpdateable) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter("game.json");
        writer.write(gson.toJson(iUpdateable));
        writer.close();
    }


    private IUpdateable readJSON() throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("game.json"));

        IUpdateable iUpdateable = gson.fromJson(bufferedReader, IUpdateable.class);
        return iUpdateable;
    }
}