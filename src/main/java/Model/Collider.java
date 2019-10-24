package Model;

/**
 * Class controlling collision between objects.
 *
 * @author Adam Rohdell
 * @version 0.1
 */
public class Collider {
    private int radius;
    private int width;
    private int height;
    private double x;
    private int y;

    private ICollidable body;


    /**
     * Updates the position in terms of x- and y-coordinates.
     *
     * @param x the objects x-coordinate.
     * @param y the objects y-coordinate.
     */
    void updatePosition(double x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Updates the size of the object.
     *
     * @param width the width to be updated.
     * @param height the height to be updated.
     */
    public void updateSize(int width, int height){
        this.width = width;
        this.height = height;
    }

    /**
     * Checks if there is a collision between objects
     *
     * @param c the collision interface.
     * @param direction the direction to be checked.
     *
     * @return true if collision, fals if not.
     */
    public boolean isColliding(ICollidable c, String direction){
        switch (direction){
            case "DOWN":
                if ((c.getCollider().getX() - this.getWidth() <= this.getX()) && (c.getCollider().getX() + c.getCollider().getWidth() >= this.getX()) && (this.getY() + this.getHeight() >= c.getCollider().getY()) && (this.getY() + this.getHeight() <= c.getCollider().getY() + c.getCollider().getHeight())){
                    return true;
                }
            case "UP":
                if ((c.getCollider().getX() - this.getWidth() <= this.getX()) && (c.getCollider().getX() + c.getCollider().getWidth() >= this.getX()) && (this.getY() == c.getCollider().getY() + c.getCollider().getHeight())){
                    return true;
                }
            case "LEFT":
                if ((this.getX() <= c.getCollider().getX() + c.getCollider().getWidth() && this.getX() >= c.getCollider().getX()) && (c.getCollider().getY() - this.getHeight() >= this.getY()) && (c.getCollider().getY() <= this.getY())){
                    return true;
                }
            case "RIGHT":
                if ((this.getX() + this.getWidth()>= c.getCollider().getX()) && (this.getX()+this.getWidth() <= c.getCollider().getX() + c.getCollider().getWidth()) && (c.getCollider().getY() - this.getHeight() >= this.getY()) && (c.getCollider().getY() <= this.getY())){
                    return true;
                }
        }

        return false;

    }

    public double getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
