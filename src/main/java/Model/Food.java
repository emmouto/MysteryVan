package Model;

import java.util.Random;

/**
 * Class handling the food appearing in the game.
 * These gives different kinds of boosts to help the player.
 *
 * @author Antonia Welzel
 * @author Jonathan Carbol
 * @version 0.1
 */
public class Food implements ICollidable{
    private String name;
    private int HP;
    private int defense;
    private int armour;

    /**
     * Constructor for food. Sets its assets.
     */
    public Food() {
        this.HP = 0;
        this.defense = 0;
        this.armour = 0;
        determineFood();
    }

    /**
     * Randomly generates any of the available food items for when one is initialized.
     */
    private void determineFood(){
        Random rand = new Random();
        int x =  rand.nextInt(5) + 1;

        switch (x){
            case 1 :    this.HP = 2;
                        this.name = "apple";
            case 2 :    this.defense = 2;
                        this.name = "orange";
            case 3 :    this.armour = 2;
                        this.name = "cheese";
            case 4 :    this.HP = 2;
                        this.defense = 2;
                        this.armour = 2;
                        this.name = "starfruit";
            case 5 :    this.HP = -1;
                        this.defense = -1;
                        this.armour = -1;
                        this.name = "rottenfruit";
        }

    }

    /**
     * Checks if a player has collided with a food item.
     *
     * @return
     */
    @Override
    public Collider getCollider() {
        return null;
    }
}
