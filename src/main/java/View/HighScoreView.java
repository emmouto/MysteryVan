package View;

import Controller.HighScoreController;
import Controller.ScreenController;
import Model.HighScore;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.resources.Resources;

/**
 * Class for creating and displaying the HighScore View.
 *
 * @author Antonia Welzel
 * @author Emma Pettersson
 * @author Jennifer Krogh
 * @version 0.1
 */
public class HighScoreView extends Screen implements IUpdateable {
    private static final BufferedImage BRICK_BG = Resources.images().get("src/main/resources/HelpView/BrickBG.png");

    private List<HighScore> highScoreList = new ArrayList<>(); //{h1, h2, h3, h4, h5, h6, h7, h8, h9, h10};

    private HighScoreController hc = new HighScoreController(highScoreList);

    /**
     * Constructor. Sets the name of the screen.
     *
     * @param screenName Name of the screen.
     */
    public HighScoreView(String screenName) {
        super(screenName);
    }

    protected void initializeComponents() {
        ScreenController screenController = new ScreenController(0, 0, 0, 0, "HighScoreView");
        this.getComponents().add(screenController);
    }

    /**
     * @param g The graphics object to render on.
     */
    @Override
    public void render(final Graphics2D g) {
        ImageRenderer.render(g, BRICK_BG, 0, 0);

        g.setColor(Color.BLACK);
        g.fillRect(300, 130, 630, 580);
        g.setColor(Color.WHITE);
        g.drawRect(300, 130, 630, 580);

        g.setFont(GameManager.PIXELED_BIG);
        g.setColor(Color.ORANGE);
        TextRenderer.render(g, "HIGH SCORES", GameManager.centerX - 320, 100);

        g.setFont(GameManager.PIXELED_XSMALL);
        TextRenderer.render(g, "PRESS ENTER TO RETURN TO MAIN MENU", GameManager.centerX - (34 * 12) / 2.0, 125);

        g.setFont(GameManager.PIXELED_MEDIUM);
        g.setColor(Color.CYAN);
        TextRenderer.render(g, "PLAYER", 320, 190);
        g.setColor(Color.MAGENTA);
        TextRenderer.render(g, "SCORE", 720, 190);

        g.setColor(Color.WHITE);
        g.setFont(GameManager.PIXELED_SMALL);

        int y = 190;
        for (HighScore hs : highScoreList) {
            TextRenderer.render(g, hs.getPlayer(), 320, (y + 50));
            TextRenderer.render(g, Integer.toString(hs.getHighScore()), 720, (y + 50));
            y += 50;
        }

        super.render(g);
    }

    /**
     * Method to call when current screen is changed to menu screen.
     */
    public static void showMenu() {
        Game.window().getRenderComponent().fadeOut(500);

        Game.loop().perform(500, () -> {
            Game.window().getRenderComponent().fadeIn(500);
            Game.screens().display("Menu");
        });
    }

    /**
     * This method is called by the game loop on all objects that need to update their attributes.
     */
    @Override
    public void update() {

    }
}
