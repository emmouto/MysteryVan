import Controller.GameController;

/**
 * Sets up all the screens and runs the game.
 *
 * @author Adam Rohdell
 * @version 0.1
 */
public class GameRunner {
    /**
     * The main function!
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        GameController gc = new GameController();
        gc.init();


    }
}
