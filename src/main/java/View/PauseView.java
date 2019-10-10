package View;

import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.gui.screens.Screen;

/**
 * ...
 *
 * @author
 * @version 0.1
 */
public class PauseView extends Screen implements IUpdateable {
    /**
     * Constructor. Sets the name of the screen.
     *
     * @param screenName Name of the screen.
     */
    public PauseView(String screenName) {
        super(screenName);
    }

    /**
     * This method is called by the game loop on all objects that need to update their attributes.
     */
    @Override
    public void update() {

    }
}
