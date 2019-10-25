package Model;

/**
 * Interface for a movable object.
 *
 * @author Adam Rohdell
 * @author Jonathan Carbol
 * @version 0.1
 */
public interface IMovable extends IUpdateable {
    double getX();
    double getY();

    /**
     * Function defining how the object moves.
     */
    void move();
}
