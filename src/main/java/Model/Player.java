package Model;

import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.Creature;

import java.util.List;

public class Player implements IMovable, ICollidable{

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


    public Player(String sprite) {
        this.sprite = sprite;
        this.collider = new Collider();
        this.collider.updatePosition(getPosX(), getPosY());
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

    public void update(){
        doGravity();
    }

    public void move(){

    }

    public void checkGrounded(List<ICollidable> collidables){
        for (ICollidable platform : collidables){
            isGrounded = collider.isColliding(platform, "DOWN");
        }
    }

    private void doGravity(){
        if (!isGrounded){
            setPosY((getY()+8));
        }
    }
}
