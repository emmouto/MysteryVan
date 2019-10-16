package Controller;

import Model.*;
import View.GameManager;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.Entity;
import de.gurkenlabs.litiengine.entities.IEntity;
import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.environment.MapObjectLoader;
import de.gurkenlabs.litiengine.graphics.IRenderable;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.util.Imaging;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class to control the food items in the game.
 */
public class FoodController implements IUpdateable {


    //TODO  collision med spelare --kolla att maten går bort
    //TODO get xgen & ygen to work properly -- platforms need pair coordinates
    //TODO evtl ändra fillCoord metoder för att bli mer abstrakt



    private List<Food> foodList = new ArrayList<>(); //probably don't need it anymore and instead change propList to foodList
    private Map map = new Map("map1");
    private List<Player> players;
    private int size = map.getPlatforms().size();
    private Collider collider;

    private List<Prop> propList = new ArrayList<>();

    private Long lastPathUpdate;


    private String name;

    // Array Lists that will contain the different x and y - values where platforms exist
    public int[] xCoords = new int[size];
    public int[] yCoords = new int[size];

    private int[][] matrixCoord = fillMatrix(xCoords, yCoords); //new int[size][size];

    public FoodController(List<Player> players) {
       // super();

        this.players = players;


        //fillXCoord(xCoords);
        //fillYCoord(yCoords);

        spawnFood();

        //updateFoodController();

        lastPathUpdate = Game.time().now();

    }



    /**
     * Spawns food in the game. Adds to the list a new food with random x and y- coordinates.
     *  (X-coord + random number 0-100) so it is not always in the same x-position on the platform,
     *  (y-coord - number) so food "floats" in the air and is not sitting on the platform.
     *  The random generating of which food will be displayed is already handled in the Food class.
     */
    public void spawnFood(){

        int rnd = new Random().nextInt(size);

        Food f = new Food(matrixCoord[rnd][0] + 30, matrixCoord[rnd][1] - 45); //genCoord(xCoords) + (new Random().nextInt(10) + 1),genCoord(yCoords) - 10
        foodList.add(f); //genCoord(xCoords) + (new Random().nextInt(100) + 1), genCoord(yCoords) - 5
        determineFoodSprite(f); //foodList.get(foodList.size()-1)
        propList.get(0).setLocation(f.getPosX(), f.getPosY());

        //propList.size()- 1).setLocation(foodList.get(foodList.size() - 1).getPosX(), foodList.get(foodList.size() - 1).getPosY()
    }

    /**
     * Method that determines which sprite to use for the food chosen.
     */
    public void determineFoodSprite(Food f){

        switch (f.getName()){
            case "beer" :
                //f.setImage(Imaging.scale(Resources.images().get("src/main/resources/Beer.png"),50));
                Prop beer = new Prop("Beer");
                propList.add(beer);
            case "bread" :
                //f.setImage(Imaging.scale(Resources.images().get("src/main/resources/Bread.png"),50));
                Prop bread = new Prop("Bread");
                propList.add(bread);
            case "rottenfruit" :
                //f.setImage(Imaging.scale(Resources.images().get("src/main/resources/AppleWorm.png"),50));
                Prop rotFruit = new Prop("AppleWorm");
                propList.add(rotFruit);

        }
        System.out.println(f.getName());

    }

    //???
    private void updateFoodController(){
        if(!foodList.isEmpty()) {
            for (int i = 0; i < foodList.size(); i++) {
                Prop p = new Prop(getFood().get(i).getName());
                propList.add(p);
                propList.get(i).setLocation(p.getX(), p.getY()); //p.getX(),p.getY()
            }
        }
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

                m[i][0] = map.getPlatforms().get(arrInd).getX() + 5; //a[arrInd];
                arrInd++;
            }

            for(int j = 0; j < b.length; j++) {
                m[j][1] = map.getPlatforms().get(bArrInd).getY(); //b[bArrInd];
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

                this.getFood().get(i).updateCollider(getFood().get(i).getPosX(), getFood().get(i).getPosY());
                System.out.println("mmm");

                if (this.getFood().get(i).checkPlayerCollision(getPlayers().get(0), getPropList().get(0).getX(), getPropList().get(0).getY())) {

                    System.out.println("xxx");

                    // removes food from the list and subsequently removes it from the game after collision
                    foodList.remove(0);
                    propList.remove(0);
                    Game.world().environment().remove(propList.get(0));
                    players.get(0).setHP(players.get(0).getHP() + foodList.get(0).getHP());
                    players.get(0).setStrength(players.get(0).getStrength() + foodList.get(0).getArmour());
                    players.get(0).setDefence(players.get(0).getDefence() + foodList.get(0).getDefense());
                    collisionUpdateValues();
                }
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

    public List<Player> getPlayers() {
        return this.players;
    }

    public List<Prop> getPropList() {
        return propList;
    }


}
