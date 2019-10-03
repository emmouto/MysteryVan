package Model;

public class Collider {

    private int radius;
    private int width;
    private int height;
    private int x;
    private int y;

    private ICollidable body;


    public Collider(){

    }

    public void updatePosition(int x, int y){
        this.x = x;
        this.y = y;
    }



    public boolean isColliding(ICollidable c, String direction){
        switch (direction){
            case "DOWN":
                if ((c.getCollider().getX() - this.getWidth() <= this.getX()) && (c.getCollider().getX() + c.getCollider().getWidth() >= this.getX()) && (this.getY() + this.getHeight() == c.getCollider().getY())){
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

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getWidth() {return this.width; }

    public int getHeight() {return this.height; }
}
