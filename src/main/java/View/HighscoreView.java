package View;

import Controller.HighscoreController;
import Controller.ScreenController;
import Model.GameManager;
import Model.Highscore;

import java.awt.*;
import java.awt.image.BufferedImage;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.resources.Resources;

/**
 * @author Antonia Welzel
 * @author Emma Pettersson
 * @author Jennifer Krogh
 */
public class HighscoreView extends Screen implements IUpdateable {
    private static final BufferedImage brickBG = Resources.images().get("src/main/resources/HelpView/BrickBG.png");

    private Highscore[] h = new Highscore[10]; //{h1, h2, h3, h4, h5, h6, h7, h8, h9, h10};

    private ScreenController screenController;
    private HighscoreController hc = new HighscoreController(h);

    /**
     * @param screenName
     *      Name of the screen.
     */
    public HighscoreView(String screenName) {
        super(screenName);
    }

    protected void initializeComponents() {
        this.screenController = new ScreenController(0, 0, 0, 0, "");
        this.getComponents().add(this.screenController);

        this.screenController.onConfirm(c -> this.showMenu());
    }

    /**
     * @param g
     *      The graphics object to render on.
     */
    @Override
    public void render(final Graphics2D g) {
        ImageRenderer.render(g, brickBG, 0, 0);

        g.setColor(Color.BLACK);
        g.fillRect(300, 130, 630, 580);
        g.setColor(Color.WHITE);
        g.drawRect(300, 130, 630, 580);

        g.setFont(GameManager.PIXELED_BIG);
        g.setColor(Color.MAGENTA);
        TextRenderer.render(g, "HIGHSCORES", GameManager.centerX - 320, 100);

        g.setFont(GameManager.PIXELED_XSMALL);
        TextRenderer.render(g, "PRESS ENTER TO RETURN TO MAIN MENU", GameManager.centerX - (34 * 12) / 2.0, 125);

        g.setFont(GameManager.PIXELED_MEDIUM);
        g.setColor(Color.CYAN);
        TextRenderer.render(g, "PLAYER", 320, 190);
        g.setColor(Color.ORANGE);
        TextRenderer.render(g, "SCORE", 720, 190);

        g.setColor(Color.WHITE);
        g.setFont(GameManager.PIXELED_SMALL);

        int y = 190;
        for (Highscore hs : h) {
            TextRenderer.render(g, hs.getPlayer(), 320, (y + 50));
            TextRenderer.render(g, Integer.toString(hs.getHighscore()), 720, (y + 50));
            y += 50;
        }

        super.render(g);
    }

    /**
     * Method to call when current screen is changed to menu screen.
     */
    private void showMenu() {
        screenController.disableController();
        Game.window().getRenderComponent().fadeOut(500);

        Game.loop().perform(500, () -> {
            Game.window().getRenderComponent().fadeIn(500);
            Game.screens().display("Menu");
        });
    }

    @Override
    public void update() {

    }
}
