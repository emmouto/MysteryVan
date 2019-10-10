package Model;

/**
 * ...
 *
 * @author
 * @version
 */
public interface IMovable extends IUpdateable {
    int getX();
    int getY();

    /**
     * ...
     */
    void move();
}
