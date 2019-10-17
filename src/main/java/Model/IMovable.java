package Model;

/**
 * Interface for a movable object.
 *
 * @author Adam Rohdell
 * @author Jonathan Carbol
 * @version 0.1
 */
public interface IMovable extends IUpdateable {
    int getX();
    int getY();

    /**
     * Function defining how the object moves.
     */
    void move();
}
