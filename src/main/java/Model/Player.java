package Model;

import java.util.List;

/**
 * The main Player class used to model the player, its movement and has other important attributes such hit points and weapon.
 * It implements IMovable and ICollidable interfaces used to check movement and collision.
 *
 * @author Jonathan Carbol
 * @author Jennifer Krogh
 * @version 0.1
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
    private int dx;
    private int dy;
    private int height;
    private int width;
    private int maxHP;
    private int score;
    private String sprite;
    private Collider collider;
    private boolean isGrounded = false;
    private boolean hasJumped = false;
    private double gravity;
    private State state;


    /**
     * The public constructor for the Player class.
     *
     * @param sprite the sprite prefix for the player.
     * @param posX the starting x position of the player.
     * @param posY the starting y position of the player.
     * @param width the width of the player.
     * @param height the height of the player.
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
        this.score = 0;
        state = State.ALIVE;
        this.maxHP = 10;
        this.setHP(maxHP);
        this.gravity=3;
        this.hasJumped = false;
    }

    /**
     * State of the player. Alive when created, dead when HP = 0;
     */
    public enum State {
        DEAD,
        ALIVE
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

    private void setPosY(int posY) {
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

    public int getMaxHP(){
        return this.maxHP;
    }

    public void setMaxHP(int maxHP){
        this.maxHP = maxHP;
    }

    public int getScore() {
        return score;
    }

    private void setScore(int score) {
        this.score = score;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    /**
     * Updates the players position and its collider.
     */
    public void update(){
        doGravity();
        updateCollider();
        updateScore();
        move();
    }

    private void updateScore(){
        this.setScore(this.getScore()+1);
    }
    /**
     * Moves the player depending on input from the user.
     */
    public void move(){
        if(Key.up.isDown && this.isGrounded ){
            this.jump();
        }
        if(Key.left.isDown && this.getX() > 0){
                this.setPosX(getX() - 2);
        }
        if(Key.right.isDown && this.getX() < 720){
                this.setPosX(getX() + 2);
        }
        isGrounded = false;
        if(gravity <= 3){
            gravity += 0.2;
        }else{
            hasJumped = false;
        }
    }

    /**
     * Makes the player jump.
     */
    private void jump(){
        this.gravity = -7;
        hasJumped = true;
    }

    /**
     * Checks if the player is standing on a platform.
     *
     * @param platforms the list of platforms to check if the player is standing on.
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
     * Updates the collider position in order to check for collisions.
     */
    private void updateCollider(){
        this.collider.updatePosition(getX(),getY());
    }

    private void notifyListeners(){

    }

    /**
     * Applies gravity to the player.
     */
    private void doGravity(){
        if (!isGrounded || hasJumped){
            setPosY(((int)(getY()+this.gravity)));
        }
    }
}
