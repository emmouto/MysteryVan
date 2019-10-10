package View;

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
 * The screen that shows when the player starts the game, shows the controls used for the game.
 *
 * @author Jennifer Krogh
 * @author Emma Pettersson
 * @version
 */
public class HelpView extends Screen implements IUpdateable {
    private static final BufferedImage BRICK_BG = Resources.images().get("src/main/resources/HelpView/BrickBG.png");
    private static final BufferedImage CONTROL_PIC = Resources.images().get("src/main/resources/HelpView/controlPic.png");
    private static final BufferedImage GO_TO_GAME = Resources.images().get("src/main/resources/HelpView/GoToGame.png");

    /**
     * @param screenName Name of the screen.
     */
    public HelpView(String screenName) {
        super(screenName);
    }

    /**
     * When enter is pressed, method calls on the change-screen method.
     */
    protected void initializeComponents() {
        ScreenController screenController = new ScreenController(0, 0, 0, 0, "");
        this.getComponents().add(screenController);
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
     * @param g The graphics object to render on.
     */
    public void render(final Graphics2D g) {
        ImageRenderer.render(g, BRICK_BG, 0, 0);
        g.setColor(Color.BLACK);
        g.fillRect(100,150,500,250);
        g.fillRect(700,150,500,250);
        g.fillRect(700,430,500,250);

        ImageRenderer.render(g, CONTROL_PIC, 180, 155);
        ImageRenderer.render(g, GO_TO_GAME, 100, 430);

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
    public static void goToSelect() {
        Game.audio().playSound(GameManager.SELECT_SOUND);
        Game.window().getRenderComponent().fadeOut(500);

        Game.loop().perform(500, () -> {
            Game.window().getRenderComponent().fadeIn(500);
            Game.screens().display("Selection");
        });
    }

    /**
     * This method is called by the game loop on all objects that need to update their attributes.
     */
    @Override
    public void update() {

    }
}
