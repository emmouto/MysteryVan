package View;

import Controller.PlayerController;
import Controller.ScreenController;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * ...
 *
 * @author Jennifer Krogh
 * @version 0.1
 */
public class PauseView extends Screen implements IUpdateable {

    private static final BufferedImage BRICK_BG = Resources.images().get("src/main/resources/HelpView/BrickBG.png");

    /**
     * Constructor. Sets the name of the screen.
     *
     * @param screenName Name of the screen.
     */

    public PauseView(String screenName) {
        super(screenName);
    }

    @Override
    protected void initializeComponents() {
        ScreenController screenController = new ScreenController(0, 0, 0, 0, "");
        this.getComponents().add(screenController);
    }

    public void render(final Graphics2D g) {

        ImageRenderer.render(g, BRICK_BG, 0, 0);

        g.setColor(Color.BLACK);
        g.fillRect(260, 230, 700, 300);
        g.setColor(Color.WHITE);
        g.drawRect(260, 230, 700, 300);

        g.setFont(GameManager.PIXELED_BIG);
        g.setColor(Color.ORANGE);
        TextRenderer.render(g, "Pause", 440, 200);

        g.setFont(GameManager.PIXELED_SMALL);
        g.setColor(Color.CYAN);
        TextRenderer.render(g, "PRESS P TO RETURN TO GAME", GameManager.centerX - (29 * 24) / 2.0, GameManager.centerY * 0.90);
        g.setColor(Color.MAGENTA);
        TextRenderer.render(g, "PRESS ENTER TO RETURN TO MENU", GameManager.centerX - (29 * 24) / 2.0, GameManager.centerY * 1.25);
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
     * Method displaying the game view when called.
     */
    public static void showGame() {
        Game.window().getRenderComponent().fadeOut(500);

        Game.loop().perform(500, () -> {
            Game.window().getRenderComponent().fadeIn(500);
            Game.screens().display("Game");
        });
    }

    /**
     * This method is called by the game loop on all objects that need to update their attributes.
     */
    @Override
    public void update() {

    }
}
