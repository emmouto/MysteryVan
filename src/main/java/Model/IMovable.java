package Model;

/**
 * TODO description
 *
 * @author
 */
public interface IMovable extends IUpdateable {
    int getX();
    int getY();

    /**
     * TODO description
     */
    void move();
}
