package Model;

import java.util.List;
import java.util.Random;

/**
 * ...
 *
 * @author Adam Rohdell
 * @version
 */
public class Enemy implements ICollidable, IMovable {
    private int HP;
  //  private Equipment equipment; //Enemy can have a weapon, armor etc that will make them harder to defeat.
    private Collider collider;
    private int height;
    private int width;
    private int x;
    private int y;
    private String sprite;
    private boolean isGrounded = false;
    private Random rand = new Random();
    private int speed;

    /**
     * ...
     *
     * @param sprite
     * @param posX
     * @param posY
     * @param width
     * @param height
     */
    public Enemy(String sprite, int posX, int posY, int width, int height){
        this.sprite = sprite;
        this.x = posX;
        this.y = posY;
        this.width = width;
        this.height = height;
        this.collider = new Collider();
        this.collider.updatePosition(posX, posY);
        this.collider.updateSize(width, height);
        this.speed = rand.nextInt(5);
        if (speed == 0){
            speed = 1;
        }
    }

    /**
     * ...
     *
     * @param platforms
     */
    public void checkGrounded(List<Platform> platforms){
        if(!isGrounded){
            for (ICollidable platform : platforms){
                if (!isGrounded){
                    isGrounded = collider.isColliding(platform, "DOWN");
                }
            }
        }
    }

    /**
     * ...
     *
     * @param player
     * @return
     */
    public boolean checkPlayerCollision(ICollidable player){
        if (collider.isColliding(player, "UP")) {
            return true;
        } else if (collider.isColliding(player, "RIGHT")) {
            return true;
        } else if (collider.isColliding(player, "DOWN")) {
            return true;
        } else if (collider.isColliding(player, "LEFT")) {
            return true;
        }

        return false;
    }

    private void doGravity(){
        if (!isGrounded){
            setY(getY()+3);
        }
    }

    private void updateCollider(){
        this.collider.updatePosition(getX(), getY());
    }

    public void update(){
        doGravity();
        updateCollider();
        move();
    }

    public void move(){
        setX(getX() + 1);
        isGrounded = false;
    }

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

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setX(int x){
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
}