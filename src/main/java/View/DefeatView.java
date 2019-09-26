package View;

import Controller.MenuController;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.input.Keyboard;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.sound.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * The screen that shows when the player has lost the game.
 *
 * @author Jennifer Krogh
 */

public class DefeatView extends Screen {

    private static final BufferedImage defeatBG = Resources.images().get("src/main/resources/DefeatView/DefeatBG.png");
    private static final BufferedImage defeatBlood1 = Resources.images().get("src/main/resources/DefeatView/DefeatBG1.png");
    private static final BufferedImage defeatBlood2 = Resources.images().get("src/main/resources/DefeatView/DefeatBGB2.png");
    private static final BufferedImage defeatBlood3 = Resources.images().get("src/main/resources/DefeatView/DefeatBGB3.png");
    private static final Sound SELECT = Resources.sounds().get("src/main/resources/audio/sfx/menu_selection.wav");
    private static final Sound gun = Resources.sounds().get("src/main/resources/DefeatView/pistol.wav");
    private int timer1 = 100;
    private int timer2 = 5;
    private int timer3 = 5;

    private MenuController defeatMenu;

    /* Change to this later?
     private Highscore hs;
     String hsOutput = Integer.toString(hs.getHighscore());
    */
    private int hs = 100;
    private String hsOutput = Integer.toString(hs);
    // Above is temporary

    public DefeatView(String screenName) {
        super(screenName);
    }

    @Override
    protected void initializeComponents() {
        final double centerX = Game.window().getResolution().getWidth() / 2.0;
        final double centerY = Game.window().getResolution().getHeight() * 1 / 2;
        final double buttonWidth = 250;

        this.defeatMenu = new MenuController(centerX - buttonWidth / 2, centerY * 1.3, buttonWidth, centerY / 2, "HIGHSCORE");
        this.getComponents().add(this.defeatMenu);

        this.defeatMenu.onConfirm(c -> {
            switch (c) {
                case 0:
                    this.showHighscore();
                    break;
                default:
                    break;
            }
        });
    }

    /**
     * Renders out the components.
     */
    public void render(final Graphics2D g) {

        if (timer1 > 0) {
            ImageRenderer.render(g, defeatBG, 0, 0);
        } else if (timer2 > 0) {
            if(timer2 == 4){
                Game.audio().playSound(gun);
            }
            ImageRenderer.render(g, defeatBlood1, 0, 0);
        } else if (timer3 > 0) {
            ImageRenderer.render(g, defeatBlood2, 0, 0);
        } else {
            ImageRenderer.render(g, defeatBlood3, 0, 0);
        }

        g.setFont(Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf", 64f));
        g.setColor(Color.RED);
        TextRenderer.render(g, "DEFEATED!", 370, 200);

        Font smallFont = new Font("arial", 1, 50);
        g.setFont(smallFont);
        g.setColor(Color.WHITE);
        TextRenderer.render(g, "Your score: " + hsOutput, 430, 300);

        Game.audio().playMusic(Resources.sounds().get("src/main/resources/DefeatView/DefeatMusic.ogg"));

        //inputName.addActionListener((ActionListener) this);

        super.render(g);

        timer1--;
        if (timer1 <= 0) {
            timer2--;
        }
        if (timer2 <= 0) {
            timer3--;
        }

    }

    private void showHighscore() {
        this.defeatMenu.setEnabled(false);
        Game.audio().playSound(SELECT);

        Game.screens().display("Highscore");
    }
}
