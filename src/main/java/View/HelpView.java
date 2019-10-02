package View;


import Controller.ScreenController;
import Model.GameManager;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.sound.Sound;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * The screen that shows when the player starts the game, shows the controls used for the game.
 *
 * @author Jennifer Krogh
 */

public class HelpView extends Screen implements IUpdateable {
    private static final BufferedImage brickBG = Resources.images().get("src/main/resources/HelpView/BrickBG.png");
    private static final BufferedImage controlPic = Resources.images().get("src/main/resources/HelpView/controlPic.png");
    private static final BufferedImage GoToGame = Resources.images().get("src/main/resources/HelpView/GoToGame.png");

    private ScreenController screenController;

    /**
     * @param screenName
     *      Name of the screen.
     */
    public HelpView(String screenName) {
        super(screenName);
    }

    /**
     * When enter is pressed, method calls on the change-screen method.
     */
    protected void initializeComponents() {

        this.screenController = new ScreenController(0, 0, 0, 0, "");
        this.getComponents().add(this.screenController);

        this.screenController.onConfirm(c -> this.goToSelect());
    }

    /**
     * Sets the music.
     */
    public void prepare() {
        Game.audio().playMusic(Resources.sounds().get("src/main/resources/audio/music/stage_select.ogg"));

        super.prepare();

        Game.loop().attach(this);
    }

    /**
     * Renders out the components.
     *
     * @param g
     */
    public void render(final Graphics2D g) {

        ImageRenderer.render(g, brickBG, 0, 0);
        g.setColor(Color.BLACK);
        g.fillRect(100,150,500,250);
        g.fillRect(700,150,500,250);
        g.fillRect(700,430,500,250);

        ImageRenderer.render(g, controlPic, 180, 155);
        ImageRenderer.render(g, GoToGame, 100, 430);

        g.setColor(Color.WHITE);
        g.drawRect(100,150,500,250);
        g.drawRect(100,430,500,250);
        g.drawRect(700,150,500,250);
        g.drawRect(700,430,500,250);

        g.setFont(Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",64f));
        g.setColor(Color.YELLOW);
        TextRenderer.render(g, "How to play", 300, 115);

        Font smallFont = new Font("arial", 1, 28);
        g.setFont(smallFont);
        g.setColor(Color.WHITE);
        TextRenderer.render(g, "Controls for movement, S-key", 720, 210);
        TextRenderer.render(g, "picks up items.", 720, 250);

        TextRenderer.render(g, "When 2 players are playing, the", 720, 320);
        TextRenderer.render(g, "key-arrows are used for player 2.", 720, 360);

        TextRenderer.render(g, "Space is used to shoot. If 2", 720, 490);
        TextRenderer.render(g, "players, dot-key is used.", 720, 530);
        TextRenderer.render(g, "Press enter to start game.", 720, 615);

        super.render(g);
    }

    /**
     * Method which changes current screen to selection.
     */
    private void goToSelect() {
        this.screenController.setEnabled(false);
        Game.audio().playSound(GameManager.SELECT_SOUND);

        Game.screens().display("Selection");
    }

    @Override
    public void update() {

    }
}
