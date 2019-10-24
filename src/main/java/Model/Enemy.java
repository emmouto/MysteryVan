package Model;

import java.util.List;
import java.util.Random;

/**
 * Class handling the games enemies.
 *
 * @author Adam Rohdell
 * @author Antonia Welzel
 * @author Jonathan Carbol
 * @version 0.1
 */
public abstract class Enemy implements ICollidable, IMovable {
    private int HP;
    //private Equipment equipment; //Enemy can have a weapon, armor etc that will make them harder to defeat.
    private Collider collider;
    private int height;
    private int width;
    private double x;
    private int y;
    private String sprite;
    private boolean isGrounded = false;
    private Random rand = new Random();
    private int speed;
    private List<Platform> platforms;
    private Player target;
    public State state;
    private int dmg;


    public enum State{
        INGAME,
        PAUSE
    }
    /**
     * Constructor for an enemy.
     *
     * @param sprite the enemy's sprite.
     * @param posX the enemy's x-coordinate.
     * @param posY the enemy's y-coordinate.
     * @param width the enemy's width.
     * @param height the enemy's height.
     */
    public Enemy(String sprite, int posX, int posY, int width, int height, int dmg){
        this.sprite = sprite;
        this.x = posX;
        this.y = posY;
        this.width = width;
        this.height = height;
        this.collider = new Collider();
        this.collider.updatePosition(posX, posY);
        this.collider.updateSize(width, height);
        this.state = State.PAUSE;
        this.dmg = dmg;
        this.speed = rand.nextInt(5);
        if (speed == 0){
            speed = 1;
        }
    }
    /**
     * Updates all aspects of an enemy.
     */
    public void update(){
        if(this.state == State.INGAME) {
            doGravity();
            updateCollider();
            if(checkPlayerCollision()){
                target.takeDamage(this.dmg);
            }
            checkGrounded();
            move();
        }
    }

    /**
     * If an enemy is not on the ground, this method makes them affected by gravity.
     */
    private void doGravity(){
        if (!isGrounded){
            setY(getY()+1);
        }
    }

    /**
     * Updates the collider.
     */
    private void updateCollider(){
        this.collider.updatePosition(getX(), getY());
    }

    /**
     * Checks if an enemy has collided with a player.
     *
     * @return true if colliding, otherwise false.
     */
    private boolean checkPlayerCollision(){
        if (collider.isColliding(target, "UP")) {
            return true;
        } else if (collider.isColliding(target, "RIGHT")) {
            return true;
        } else if (collider.isColliding(target, "DOWN")) {
            return true;
        } else if (collider.isColliding(target, "LEFT")) {
            return true;
        }

        return false;
    }

    /**
     * Checks if the enemy is on the ground or a platform.
     */
    private void checkGrounded(){
        if(!isGrounded){
            for (ICollidable platform : platforms){
                if (!isGrounded){
                    isGrounded = collider.isColliding(platform, "DOWN");
                }
            }
        }
    }

    /**
     * Moves the enemy.
     */
    public abstract void move();

    public int getHP() {
        return HP;
    }

   // public Equipment getWeapon() {
    //    return equipment;
   // }

    public void setHP(int HP) {
        this.HP = HP;
    }

   // public void setWeapon(Equipment weapon) {
    //    this.equipment = weapon;
    //}

    @Override
    public Collider getCollider() {
        return this.collider;
    }

    public double getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public String getSprite(){
        return this.sprite;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setPlatforms(List<Platform> platforms){
        this.platforms = platforms;
    }

    public void setTarget(Player p){
        this.target = p;
    }

    public Player getTarget(){
        return this.target;
    }

    public boolean isGrounded(){
        return this.isGrounded;
    }

    public void setGrounded(boolean grounded){
        this.isGrounded = grounded;
    }
}