package Model;

public class Collider {

    private int radius;
    private int width;
    private int height;
    private int x;
    private int y;

    private IMovable body;


    public Collider(IMovable body){
        this.body = body;

    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }
}
