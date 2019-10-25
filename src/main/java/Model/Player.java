package Model;

import java.util.List;

/**
 * The main Player class used to model the Player,
 * its movement and other important attributes such hit points and weapon.
 * It implements the IMovable and ICollidable interfaces, to check movement and collision.
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
    private double posX;
    private double posY;
    private int height;
    private int width;
    private int maxHP;
    private int score;
    private String sprite;
    private Collider collider;
    public boolean isGrounded = false;
    private boolean hasJumped = false;
    private double gravity;
    private State state;
    private Direction direction;
    private long time;
    private long timeSinceDamage;
    private List<Platform> platforms;
    private String walkingSprite;
    private String idleSprite;
    private String attackSprite;

    enum Direction{
        LEFT,
        RIGHT
    }

    /**
     * The public constructor for the Player class.
     *
     * @param sprite the sprite prefix for the player.
     * @param posX the starting x position of the player.
     * @param posY the starting y position of the player.
     * @param width the width of the player.
     * @param height the height of the player.
     */
    public Player(String sprite, int posX, int posY, int width, int height, List<Platform> platforms) {
        this.sprite = sprite;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.platforms = platforms;
        this.collider = new Collider();
        this.collider.updatePosition(posX, posY);
        this.collider.updateSize(width, height);
        this.score = 0;
        this.state = State.ALIVE;
        this.maxHP = 10;
        this.setHP(maxHP);
        this.gravity=3;
        this.hasJumped = false;
        this.direction = Direction.RIGHT;
        this.time = System.currentTimeMillis();
        this.idleSprite = this.sprite;
        this.attackSprite = this.sprite + "attack";
        this.walkingSprite = this.sprite + "walk";
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

    public double getX() {
        return posX;
    }

    public void setX(double posX) {
        this.posX = posX;
    }

    public double getY() {
        return posY;
    }

    public void setY(double posY) {
        this.posY = posY;
    }

    public int getMaxHP(){
        return this.maxHP;
    }

    public void setMaxHP(int maxHP){
        this.maxHP = maxHP;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Updates the players position and its collider.
     */
    public void update() {
        checkGrounded();
        doGravity();
        updateCollider();
        updateScore();
        attack();
        move();
        if(System.currentTimeMillis() - time > 1200){
            this.setSprite(this.idleSprite);
        }
    }

    /**
     * Updates the score of the Player.
     */

    private void updateScore(){
        this.setScore(this.getScore());
    }


    /**
     * Moves the player depending on input from the user.
     */
    public void move() {
        if (Key.up.isDown && this.isGrounded ) {
            this.jump();
        }

        if (Key.left.isDown && this.getX() > 0) {
                this.setX(getX() - 2);
                this.setDirection(Direction.LEFT);
                if(!this.getSprite().equals(this.walkingSprite)) {
                    this.setSprite(this.walkingSprite);
                    time = System.currentTimeMillis()-1500;
                }
        }else if (Key.right.isDown && this.getX() < 720) {
                this.setX(getX() + 2);
                this.setDirection(Direction.RIGHT);
                if(!this.getSprite().equals(this.walkingSprite)){
                    this.setSprite(this.walkingSprite);
                    time = System.currentTimeMillis()-1500;
                }
        }

        isGrounded = false;

        if (gravity <= 3) {
            gravity += 0.2;
        } else {
            hasJumped = false;
        }
        if(this.getX()< -10 || this.getX() > 720 || this.getY() > 450){
            this.setState(State.DEAD);
        }
    }

    /**
     * Makes the player jump.
     */

    public void jump(){
        this.gravity = -7;
        hasJumped = true;
    }

    /**
     * Performs an attack and deals damage to enemies within range.
     */
    private void attack(){
        if(Key.attack.isDown && System.currentTimeMillis()-time > 1500){
            for(int i = 0; i < GameLoop.getInstance().getEnemies().size(); i++) {
                Enemy e = GameLoop.getInstance().getEnemies().get(i);
                System.out.println(e.getHP());
                if(this.getDirection() == Direction.LEFT && e.getX() > this.getX()- 5 * this.getWeapon().getRange() && e.getX() < this.getX() + 50 && this.getY() + 50 > e.getY() && this.getY() - 50 < e.getY()){
                    dealDamage(e);
                }else if(this.getDirection() == Direction.RIGHT && e.getX() < this.getX()+ 5 * this.getWeapon().getRange() && e.getX() > this.getX() - 50 &&  this.getY() + 50 > e.getY() && this.getY() - 50 < e.getY()){
                    dealDamage(e);
                }
            }
            if(!this.getSprite().equals(this.attackSprite)) {
                this.setSprite(this.attackSprite);
            }
            time = System.currentTimeMillis();
        }

    }

    /**
     * Deals damage to an enemy and adds score if an enemy is killed.
     * @param e the enemy that is being dealt damage to.
     */
    private void dealDamage(Enemy e){
        e.setHP(e.getHP()-this.getWeapon().getDamage()*this.getStrength()/10);
        if(e.getHP() <= 0){
            this.setScore(this.getScore()+10);
        }
    }

    /**
     * Deals damage to the player.
     * @param dmg the amount of damage an enemy deals.
     */
    public void takeDamage(int dmg){
        if(System.currentTimeMillis()-timeSinceDamage > 1000) {
            this.setHP(this.getHP()+this.getDefence()/20 - dmg);
            timeSinceDamage = System.currentTimeMillis();
        }
    }

    /**
     * Checks if the player is standing on a platform.
     */
    private void checkGrounded(){
        if (!isGrounded) {
            for (ICollidable platform : this.platforms) {
                if (!isGrounded) {
                    isGrounded = collider.isColliding(platform, "DOWN");
                }
            }
        }
    }

    /**
     * Updates the collider position in order to check for collisions.
     */
    private void updateCollider() {
        this.collider.updatePosition(getX(), (int) getY());
    }

    /**
     * Applies gravity to the player.
     */
    public void doGravity() {
        if (!isGrounded || hasJumped) {
            setY(((int)(getY() + this.gravity)));
        }
    }
}
