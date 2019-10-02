package Model;

public class Platform implements ICollidable{

    // Side platforms are 160px wide and 48px high
    // Middle platform is 288px wide and 16px high
    private Collider collider;
    private int x;
    private int y;
    private int width;
    private int height;



    public Platform(int x, int y, int height, int width){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        collider = new Collider();
        collider.updatePosition(x, y);
    }

    @Override
    public Collider getCollider() {
        return this.collider;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
