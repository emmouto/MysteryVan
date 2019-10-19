import Controller.*;
import de.gurkenlabs.litiengine.Game;

import java.io.IOException;

/**
 * Sets up all the screens and runs the game.
 *
 * @author Jonathan Carbol
 * @author Jennifer Krogh
 * @author Emma Pettersson
 * @author Adam Rohdell
 * @author Antonia Welzel
 * @version 0.1
 */
public class GameRunner {
    /**
     * The main function!
     *
     * @param args the command line arguments.
     * @throws java.io.IOException when...
     */
    public static void main(String[] args) throws IOException {
        GameController gc = new GameController();
        gc.init();


    }
}
