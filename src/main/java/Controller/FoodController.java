package Controller;

import Model.*;
import Model.Maps.MapHard;
import View.GameManager;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.Prop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Class to control the food items in the game.
 *
 * @author Antonia
 **/

 public class FoodController implements IUpdateable {

    private List<Food> foodList = new ArrayList<>();
    private List<Player> players;
    private List<Prop> propList = new ArrayList<>();

    private MapController mc = new MapController();
    private Map map = new MapHard();

    private int size = map.getPlatforms().size();

    // Array Lists that will contain the different x and y values where platforms exist
    private int[] xCoords = new int[size];
    private int[] yCoords = new int[size];

    // Matrix array which contains the x and y coordinates of platforms
    private int[][] matrixCoord = fillMatrix(xCoords, yCoords);


    public FoodController(List<Player> players) {
        super();

        this.players = players;
        spawnFood();

    }



    /**
     * Spawns food in the game. Adds to the list a new food with random x and y- coordinates.
     *  (X-coord + random number 0-20) so it is not always in the same x-position on the platform,
     *  (y-coord - 45) so food "floats" in the air and is not sitting on the platform.
     *  The random generating of which food will be displayed is already handled in the Food class.
     */
    public void spawnFood(){

        foodList.clear();
        propList.clear();

        int rnd = new Random().nextInt(size);
        int rndX = new Random().nextInt(10);

        Food f = new Food(matrixCoord[rnd][0] + rndX, matrixCoord[rnd][1] - 45, getPlayers().get(0)); //matrixCoord[rnd][0] + rndX, matrixCoord[rnd][1] - 45
        foodList.add(f);
        determineFoodSprite(f);
        propList.get(0).setLocation(f.getPosX(), f.getPosY());


    }

    /**
     * Method that determines which sprite to use for the chosen food.
     */
    public void determineFoodSprite(Food f){

        switch (f.getName()){
            case "beer" :
                Prop beer = new Prop("Beer");
                propList.add(beer);
            case "bread" :
                Prop bread = new Prop("Bread");
                propList.add(bread);
            case "rottenfruit" :
                Prop rotFruit = new Prop("AppleWorm");
                propList.add(rotFruit);

        }

    }


    /**
     * Method that fills a matrix with the x and y values of platforms in the game map.
     * @param a array of platforms' x coordinates
     * @param b array of platforms' y coordinates
     * @return returns a matrix with the x and y coordinates of the current platforms
     */
    public int[][] fillMatrix(int[] a, int[] b) {

        int[][] m = new int[a.length][2];

        int arrInd = 0;
        int bArrInd = 0;

            for(int i = 0; i < a.length; i++) {

                m[i][0] = map.getPlatforms().get(arrInd).getX() + 5;
                arrInd++;
            }

            for(int j = 0; j < b.length; j++) {
                m[j][1] = map.getPlatforms().get(bArrInd).getY();
                bArrInd++;
            }


        return m;
    }


    /**
     * Method updates the item in the game and handles its collision with a player.
     */
    @Override
    public void update() {
        if (GameManager.getState() == GameManager.GameState.INGAME) {
            for (int i = 0; i < this.getFood().size(); i++) {
                
                GameLoop.getInstance().setDelayTimer(6000);
                if(GameLoop.getInstance().checkIfDelayDone()) {

                    // removes food from the list and subsequently removes it from the game after collision
                    getFood().remove(0);
                    Game.world().environment().remove(getPropList().get(0));
                    getPropList().remove(0);

                    // Spawn a new food when the player has taken the previous one
                    spawnFood();
                    Game.world().environment().add(getPropList().get(0));
                }


                if (getFood().get(i).collided) {
                    // removes food from the list and subsequently removes it from the game after collision
                    getFood().remove(0);
                    Game.world().environment().remove(getPropList().get(0));
                    getPropList().remove(0);

                    // Spawn a new food when the player has taken the previous one
                    spawnFood();
                    Game.world().environment().add(getPropList().get(0));
                }
            }

        }
    }

    public List<Food> getFood() {
        return foodList;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public List<Prop> getPropList() {
        return propList;
    }




}
