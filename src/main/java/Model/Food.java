package Model;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Class that represents food in the game, which is a source for the player
 * to recharge one of its three features - HP, defence and strength/armour.
 */
public class Food implements ICollidable{

    private String name;
    private int HP;
    private int defense;
    private int armour;
    private int posX;
    private int posY;
    private BufferedImage image;

    private Collider collider;

    public Food(int posX, int posY) {
        this.HP = 0;
        this.defense = 0;
        this.armour = 0;
        this.image = null;
        this.posX = posX;
        this.posY = posY;
        determineFood();
    }

    /**
     * Method that chooses a food at random and sets the assigned features.
     */
    public void determineFood(){
        Random rand = new Random();
        int x =  rand.nextInt(2)+1;
        switch (x){
            case 1 :    this.HP = 2;
                this.name = "beer";
                break;
            case 2 :    this.defense = 2;
                this.name = "bread";
                break;
            /*case 3 :    this.armour = 2;
                this.name = "cheese";
            case 4 :    this.HP = 2;
                this.defense = 2;
                this.armour = 2;
                this.name = "starfruit";*/
            case 3 :    this.HP = -1;
                this.defense = -1;
                this.armour = -1;
                this.name = "rottenfruit";
                break;
        }

        System.out.println(x);

    }




    /**
     * Updates the Collider postion
     **/

    public void updateCollider(int x, int y) {

       this.collider.updatePosition(x, y);
    }

    /**
     * Method which checks if there is a collision with a player.
     * @param player The player involved in the collision
     * @return returns a boolean value for whether or not a collision has taken place
     */
        public boolean checkPlayerCollision(ICollidable player, double x, double y){ //, int x, int y

            //this.collider.updatePosition((int) x, (int) y);

            if (collider.isColliding(player, "UP")){
            System.out.println("yes");
            return true;
        } else if (collider.isColliding(player, "RIGHT")){
            System.out.println("yes");
            return true;
        } else if(collider.isColliding(player, "DOWN")){
            System.out.println("yes");
            return true;
        } else if (collider.isColliding(player, "LEFT")){
            System.out.println("yes");
            return true;
        }

        return false;
    }

    public void update() {

    }


    @Override
    public Collider getCollider() {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getArmour() {
        return armour;
    }

    public int getDefense() {
        return defense;
    }

    public void setArmour(int armour) {
        this.armour = armour;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
