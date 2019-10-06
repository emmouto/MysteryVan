package Model;

import java.util.List;

/**
 * TODO description
 *
 * @author
 */
public class Player implements IMovable, ICollidable{
    private String name;
    private int HP;
    private int strength;
    private int defence;
    private Weapon weapon;
    private Hat hat;
    private Boost boost1;
    private Boost boost2;
    private int posX;
    private int posY;
    private int height;
    private int width;
    private String sprite;
    private Collider collider;
    private boolean isGrounded = false;

    /**
     * TODO description
     *
     * @param sprite
     *      TODO description
     * @param posX
     *      TODO description
     * @param posY
     *      TODO description
     * @param width
     *      TODO description
     * @param height
     *      TODO description
     */
    public Player(String sprite, int posX, int posY, int width, int height) {
        this.sprite = sprite;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.collider = new Collider();
        this.collider.updatePosition(posX, posY);
        this.collider.updateSize(width, height);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return HP;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefence() {
        return defence;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Hat getHat() {
        return hat;
    }

    public Boost getBoost1() {
        return boost1;
    }

    public Boost getBoost2() {
        return boost2;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setHat(Hat hat) {
        this.hat = hat;
    }

    public void setBoost1(Boost boost1) {
        this.boost1 = boost1;
    }

    public void setBoost2(Boost boost2) {
        this.boost2 = boost2;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String name){
        this.sprite = name;
    }

    @Override
    public Collider getCollider() {
        return this.collider;
    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }

    /**
     * TODO description
     */
    public void update() {
        doGravity();
        updateCollider();
    }

    /**
     * TODO description
     */
    public void move() {

    }

    /**
     * TODO description
     *
     * @param platforms
     *      TODO description
     */
    public void checkGrounded(List<Platform> platforms) {
        if(!isGrounded){
            for (ICollidable platform : platforms){
                if (!isGrounded){
                    isGrounded = collider.isColliding(platform, "DOWN");
                }
            }
        }
    }

    private void updateCollider() {
        this.collider.updatePosition(getX(),getY());
    }

    private void doGravity(){
        if (!isGrounded){
            setPosY((getY()+3));
        }
    }
}
