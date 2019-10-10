package Model;

/**
 * The Platform class used in the model.
 *
 * @author Jonathan Carbol
 * @version
 */
public class Platform implements ICollidable{
    // Side platforms are 160px wide and 48px high
    // Middle platform is 288px wide and 16px high
    private Collider collider;
    private int x;
    private int y;
    private int width;
    private int height;

    /**
     * The public constructor of the Platform class.
     *
     * @param x the x position of the platform.
     * @param y the y position of the platform.
     * @param height the height of the platform.
     * @param width the width of the platform.
     */
    public Platform(int x, int y, int height, int width){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        collider = new Collider();
        collider.updatePosition(x, y);
        collider.updateSize(width, height);
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
