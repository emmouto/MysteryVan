package Model;

/**
 *  The Key class, which are connected to key inputs.
 *
 * @author Jonathan Carbol
 * @version 0.1
 */
public class Key{
    // Creating the keys as simply variables
    public static Key up = new Key();
    public static Key down = new Key();
    public static Key left = new Key();
    public static Key right = new Key();
    public static Key special = new Key();
    public static Key pause = new Key();
    public static Key enter = new Key();

    /**
     *  Toggles the keys current state.
     */
    public void toggle(){
        isDown =  !isDown;
    }

    public boolean isDown;
}