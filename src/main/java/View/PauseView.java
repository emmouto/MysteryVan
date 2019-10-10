package View;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;

import java.awt.*;

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

    public void render(final Graphics2D g) {

        g.setFont(GameManager.PIXELED_BIG);
        g.setColor(Color.WHITE);
        TextRenderer.render(g, "Pause", 370, 200);

    }

    /**
     * Method displaying the pause view when called.
     */
    public static void showPause() {
        Game.window().getRenderComponent().fadeOut(500);

        Game.loop().perform(500, () -> {
            Game.window().getRenderComponent().fadeIn(500);
            Game.screens().display("Pause");
        });
    }


    /**
     * This method is called by the game loop on all objects that need to update their attributes.
     */
    @Override
    public void update() {

    }
}
