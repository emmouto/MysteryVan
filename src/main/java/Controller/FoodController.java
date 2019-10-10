package Controller;

import Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class to control the food items in the game.
 */
public class FoodController implements IUpdateable {


    //TODO få in bilden till food sprite, collision med spelare --kolla att maten går bort
    //TODO evtl ändra fillCoord metoder för att bli mer abstrakt


    private List<Food> foodList = new ArrayList<>();
    private Map map = new Map("map1");
    private List<Player> players;
    private int size = map.getPlatforms().size();


    // Array Lists that will contain the different x and y - values where platforms exist
    public int[] xCoords = new int[size];
    public int[] yCoords = new int[size];

    public FoodController() {
        super();
        spawnFood();
        fillXCoord(xCoords);
        fillYCoord(yCoords);

    }

    /**
     * Spawns food in the game. Adds to the list a new food with random x and y- coordinates.
     *  (X-coord + random number 0-100) so it is not always in the same x-position on the platform,
     *  (y-coord - number) so food "floats" in the air and is not sitting on the platform.
     *  The random generating of which food will be displayed is already handled in the Food class.
     */
    public void spawnFood(){
        foodList.add(new Food(genCoord(xCoords) + (new Random().nextInt(100) + 1), genCoord(yCoords) - 5));
    }

    /**
     * Method that makes sure that a player's characteristics' values do not exceed a specific maximum value
     * after the collision between food and the player.
     */
    public void collisionUpdateValues(){

        int maxHP = 40;
        int maxStrength = 50;
        int maxDefence = 50;

        if(players.get(0).getHP() > maxHP) {
            players.get(0).setHP(maxHP);
        } else if(players.get(0).getStrength() > maxStrength) {
            players.get(0).setStrength(maxStrength);
        } else if(players.get(0).getDefence() > maxDefence) {
            players.get(0).setDefence(maxDefence);
        }

    }

    /**
     * Method fills the xCoords array with the platforms-List's x-positions
     * and adds to it so item is later on more centered on the platform.
     * @param arr Array to be filled
     * @return returns the finished array
     */
    public int[] fillXCoord(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = map.getPlatforms().get(i).getX() + 20;
        }

        return arr;
    }

    /**
     * Method fills the yCoords array with the platforms-List's y-positions.
     * @param arr Array to be filled
     * @return returns the finished array
     */
    public int[] fillYCoord(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = map.getPlatforms().get(i).getY();
        }

        return arr;
    }

    // Method which chooses a value from an array randomly
    private int genCoord(int[] spots) {
        int rnd = new Random().nextInt(spots.length);
        return spots[rnd];
    }

    /**
     * Method updates the item in the game and handles its collision with a player.
     */
    @Override
    public void update() {
        for(int i = 0; i < getFood().size(); i++) {
            if(this.getFood().get(i).checkPlayerCollision(players.get(0))) {
                // removes food from the list and subsequently removes it from the game after collision
                foodList.remove(0);
                players.get(0).setHP(players.get(0).getHP() + foodList.get(0).getHP());
                players.get(0).setStrength(players.get(0).getStrength() + foodList.get(0).getArmour());
                players.get(0).setDefence(players.get(0).getDefence() + foodList.get(0).getDefense());
                collisionUpdateValues();
            }
        }

    }

    public List<Food> getFood() {
        return foodList;
    }

    /**
     * Method which loads the map with the right components.
     * @param map current Map which is defined in GameRunner
     */
    public void loadMap(Map map) {
        this.map = map;
    }


}
