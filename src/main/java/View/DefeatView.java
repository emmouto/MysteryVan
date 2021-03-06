package View;

import Controller.ScreenController;

import java.awt.*;
import java.awt.image.BufferedImage;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.sound.Sound;

/**
 * The screen that shows when the player has lost the game.
 *
 * @author Jennifer Krogh
 * @author Emma Pettersson
 * @version 0.1
 */

public class DefeatView extends Screen {
    private final static BufferedImage DEFEAT_BG = Resources.images().get("src/main/resources/DefeatView/DefeatBG.png");
    private final static BufferedImage DEFEAT_BLOOD_1 = Resources.images().get("src/main/resources/DefeatView/DefeatBG1.png");
    private final static BufferedImage DEFEAT_BLOOD_2 = Resources.images().get("src/main/resources/DefeatView/DefeatBGB2.png");
    private final static BufferedImage DEFEAT_BLOOD_3 = Resources.images().get("src/main/resources/DefeatView/DefeatBGB3.png");
    private final static Sound GUN = Resources.sounds().get("src/main/resources/DefeatView/pistol.wav");
    private final static Sound DEFEAT_THEME = Resources.sounds().get("src/main/resources/DefeatView/DefeatMusic.ogg");

    private int timer1 = 100;
    private int timer2 = 5;
    private int timer3 = 5;

    private int score;

    /**
     * Constructor. Sets the name of the screen.
     *
     * @param screenName name of the screen.
     */
    public DefeatView(String screenName) {
        super(screenName);
    }

    @Override
    protected void initializeComponents() {
        ScreenController screenController = new ScreenController(0, 0, 0, 0, "");
        this.getComponents().add(screenController);
    }

    /**
     * Renders out the components.
     *
     * @param g the graphics object to render on.
     */
    public void render(final Graphics2D g) {
        if (timer1 > 0) {
            ImageRenderer.render(g, DEFEAT_BG, 0, 0);
        } else if (timer2 > 0) {
            if (timer2 == 4) {
                Game.audio().playSound(GUN);
            }

            ImageRenderer.render(g, DEFEAT_BLOOD_1, 0, 0);
        } else if (timer3 > 0) {
            ImageRenderer.render(g, DEFEAT_BLOOD_2, 0, 0);
        } else {
            ImageRenderer.render(g, DEFEAT_BLOOD_3, 0, 0);
        }

        g.setFont(GameManager.PIXELED_BIG);
        g.setColor(Color.RED);
        TextRenderer.render(g, "DEFEATED!", 370, 200);

        g.setFont(GameManager.RAINY_MEDIUM);
        g.setColor(Color.WHITE);
        TextRenderer.render(g, "Your score: " + this.score, 430, 300);

        g.setFont(GameManager.PIXELED_SMALL);
        TextRenderer.render(g, "PRESS ENTER TO SEE HIGH SCORES", GameManager.centerX - (29 * 24) / 2.0, GameManager.centerY * 1.3);

        Game.audio().playMusic(DEFEAT_THEME);

        super.render(g);

        timer1--;
        if (timer1 <= 0) {
            timer2--;
        } if (timer2 <= 0) {
            timer3--;
        }
    }

    /**
     * Call this method when the game has ended to set the score for the screen.
     *
     * @param score the score that the <code>Player</code> has at the end of the game.
     */
    public void scoreDefeat(int score) {
        this.score = score;
    }

}
