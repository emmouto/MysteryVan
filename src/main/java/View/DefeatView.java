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
 */

public class DefeatView extends Screen {
    private final BufferedImage defeatBG = Resources.images().get("src/main/resources/DefeatView/DefeatBG.png");
    private final BufferedImage defeatBlood1 = Resources.images().get("src/main/resources/DefeatView/DefeatBG1.png");
    private final BufferedImage defeatBlood2 = Resources.images().get("src/main/resources/DefeatView/DefeatBGB2.png");
    private final BufferedImage defeatBlood3 = Resources.images().get("src/main/resources/DefeatView/DefeatBGB3.png");
    private final Sound GUN = Resources.sounds().get("src/main/resources/DefeatView/pistol.wav");
    private final Sound DEFEAT_THEME = Resources.sounds().get("src/main/resources/DefeatView/DefeatMusic.ogg");

    private int timer1 = 100;
    private int timer2 = 5;
    private int timer3 = 5;

    private ScreenController screenController;

    // TODO Change this so it takes in the player's achieved score
    private int hs = 100;
    private String hsOutput = Integer.toString(hs);
    // Above is temporary.

    /**
     * @param screenName
     *      Name of the screen.
     */
    public DefeatView(String screenName) {
        super(screenName);
    }

    @Override
    protected void initializeComponents() {
        this.screenController = new ScreenController(0, 0, 0, 0, "");
        this.getComponents().add(this.screenController);

        this.screenController.onConfirm(c -> this.showHighscore());
    }

    /**
     * Renders out the components.
     *
     * @param g
     *      The graphics object to render on.
     */
    public void render(final Graphics2D g) {
        if (timer1 > 0) {
            ImageRenderer.render(g, defeatBG, 0, 0);
        } else if (timer2 > 0) {
            if (timer2 == 4) {
                Game.audio().playSound(GUN);
            }

            ImageRenderer.render(g, defeatBlood1, 0, 0);
        } else if (timer3 > 0) {
            ImageRenderer.render(g, defeatBlood2, 0, 0);
        } else {
            ImageRenderer.render(g, defeatBlood3, 0, 0);
        }

        g.setFont(ScreenController.PIXELED_BIG);
        g.setColor(Color.RED);
        TextRenderer.render(g, "DEFEATED!", 370, 200);

        g.setFont(ScreenController.RAINY_MEDIUM);
        g.setColor(Color.WHITE);
        TextRenderer.render(g, "Your score: " + hsOutput, 430, 300);

        g.setFont(ScreenController.PIXELED_SMALL);
        TextRenderer.render(g, "PRESS ENTER TO SEE HIGHSCORES", ScreenController.centerX - (29 * 24) / 2.0, ScreenController.centerY * 1.3);

        Game.audio().playMusic(DEFEAT_THEME);

        super.render(g);

        timer1--;
        if (timer1 <= 0) {
            timer2--;
        } if (timer2 <= 0) {
            timer3--;
        }
    }

    private void showHighscore() {
        this.screenController.setEnabled(false);
        Game.audio().playSound(ScreenController.SELECT_SOUND);
        Game.window().getRenderComponent().fadeOut(500);

        Game.loop().perform(500, () -> {
            Game.window().getRenderComponent().fadeIn(500);
            Game.screens().display("Highscore");
        });
    }
}
