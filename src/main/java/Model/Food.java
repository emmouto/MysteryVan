package Model;

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
    private Collider collider;


    public Food(int posX, int posY) {
        this.HP = 0;
        this.defense = 0;
        this.armour = 0;

        this.posX = posX;
        this.posY = posY;

        determineFood();

        this.collider = new Collider();
        this.collider.updatePosition(posX,posY);
        this.collider.updateSize(16,16);
    }

    /**
     * Method that chooses a food at random and sets the assigned features.
     */
    public void determineFood(){
        Random rand = new Random();
        int x =  rand.nextInt(3)+1;
        switch (x){
            case 1 :    this.HP = 2;
                this.name = "beer";
                break;
            case 2 :    this.defense = 2;
                this.name = "bread";
                break;
            case 3 :    this.HP = -1;
                this.defense = -1;
                this.armour = -1;
                this.name = "rottenfruit";
                break;

                /*case 4 :    this.armour = 2;
                this.name = "cheese";
                this.HP = 2;
                this.defense = 2;
                this.armour = 2;
                this.name = "starfruit";*/
        }

        System.out.println(x);

    }


    /**
     * Updates the Collider postion
     **/
    public void updateCollider(double x, double y) {

       this.collider.updatePosition((int) x, (int) y); //getPosX(), getPosY()
    }

    /**
     * Method which checks if there is a collision with a player.
     * @param player The player involved in the collision
     * @return returns a boolean value for whether or not a collision has taken place
     */
        public boolean checkPlayerCollision(ICollidable player) {

            if (collider.isColliding(player, "UP")){
            return true;

        } else if (collider.isColliding(player, "RIGHT")){
            return true;

        } else if(collider.isColliding(player, "DOWN")){
            return true;

        } else if (collider.isColliding(player, "LEFT")){
            return true;
        }

        return false;
    }


    /**
     * Updates the Collider
     **/
    public void update() {

            updateCollider(posX, posY);
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

}
