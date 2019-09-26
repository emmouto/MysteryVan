package View;

import Controller.HighscoreController;
import Controller.ScreenController;

import java.awt.*;

import Model.Highscore;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;

/**
 * @author Antonia Welzel
 * @author Emma Pettersson
 */
public class HighscoreView extends Screen implements IUpdateable {
    private Highscore[] h = new Highscore[10]; // {h1, h2, h3, h4, h5, h6, h7, h8, h9, h10};

    private HighscoreController hc = new HighscoreController(h);
    private ScreenController screenController;

    /**
     * @param screenName
     *      Name of the screen.
     */
    public HighscoreView(String screenName) {
        super(screenName);
    }

    @Override
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
        g.setFont(ScreenController.PIXELED_BIG);
        g.setColor(Color.MAGENTA);
        TextRenderer.render(g, "HIGHSCORES", ScreenController.centerX - 320, 100);

        g.setFont(ScreenController.PIXELED_MEDIUM);
        g.setColor(Color.CYAN);
        TextRenderer.render(g, "PLAYER", 320, 190);
        g.setColor(Color.ORANGE);
        TextRenderer.render(g, "SCORE", 720, 190);

        g.setColor(Color.WHITE);
        g.setFont(ScreenController.PIXELED_SMALL);
        int y = 190;
        for (Highscore hs : h) {
            TextRenderer.render(g, hs.getPlayer(), 320, (y + 50));
            TextRenderer.render(g, Integer.toString(hs.getHighscore()), 720, (y + 50));
            y += 50;
        }

        g.drawRect(300, 130, 630, 580);

        g.setColor(Color.PINK);
        g.setFont(ScreenController.PIXELED_XSMALL);
        TextRenderer.render(g, "PRESS ENTER TO RETURN TO MAIN MENU", ScreenController.centerX - (34 * 12) / 2.0, 125);

        super.render(g);
    }

    /**
     * Method to call when current screen is changed to menu screen.
     */
    private void showMenu() {
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


