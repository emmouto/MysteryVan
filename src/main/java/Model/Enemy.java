package Model;
import java.util.List;


public class Enemy implements ICollidable, IMovable {

    private int HP;
    private Equipment equipment; //Enemy can have a weapon, armor etc that will make them harder to defeat.
    private Collider collider;
    private int height;
    private int width;
    private int x;
    private int y;
    private String sprite;
    private boolean isGrounded = false;




    public Enemy(String sprite){
        this.sprite = sprite;
    }


    public void checkGrounded(List<ICollidable> collidables){
        for (ICollidable platform : collidables){
            isGrounded = collider.isColliding(platform, "DOWN");
        }
    }

    private void doGravity(){
        if (!isGrounded){
            setY((int)getX()+10);
        }
    }

    public void update(){
        doGravity();
    }

    private void move(){
        setX((int)getX() + 1);
    }

    public int getHP() {
        return HP;
    }

    public Equipment getWeapon() {
        return equipment;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setWeapon(Equipment weapon) {
        this.equipment = weapon;
    }


    @Override
    public Collider getCollider() {
        return this.collider;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
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