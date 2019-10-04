package Model;

/**
 * TODO description
 *
 * @author
 */
public class Collider {
    private int radius;
    private int width;
    private int height;
    private int x;
    private int y;

    private ICollidable body;

    /**
     * TODO description
     */
    public Collider(){

    }

    /**
     * TODO description
     *
     * @param x
     *      TODO description
     * @param y
     *      TODO description
     */
    public void updatePosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * TODO description
     *
     * @param width
     *      TODO description
     * @param height
     *      TODO description
     */
    public void updateSize(int width, int height){
        this.width = width;
        this.height = height;
    }

    /**
     * TODO description
     *
     * @param c
     *      TODO description
     * @param direction
     *      TODO description
     *
     * @return
     *      TODO description
     */
    public boolean isColliding(ICollidable c, String direction){
        switch (direction){
            case "DOWN":
                if ((c.getCollider().getX() - this.getWidth() <= this.getX()) && (c.getCollider().getX() + c.getCollider().getWidth() >= this.getX()) && (this.getY() + this.getHeight() >= c.getCollider().getY()) && (this.getY() + this.getHeight() <= c.getCollider().getY() + c.getCollider().getHeight())){
                    System.out.println("memes");
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

    // Getters - JavaDoc not needed
    public int getX(){
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
