package View;

import Controller.ScreenController;
import Model.GameManager;

import java.awt.*;
import java.awt.image.BufferedImage;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.sound.Sound;

/**
 * The game's title screen.
 *
 * @author Emma Pettersson
 * @version
 */
public class MenuView extends Screen implements IUpdateable {
    private static final Sound TITLE_THEME = Resources.sounds().get("src/main/resources/audio/music/title_theme.mp3");

    private ScreenController screenController;

    /**
     * Constructor. Sets the name of the screen.
     *
     * @param screenName Name of the screen.
     */
    public MenuView(String screenName) {
        super(screenName);
    }

    @Override
    protected void initializeComponents() {
        final double buttonWidth = 250;

        this.screenController = new ScreenController(GameManager.centerX - buttonWidth / 2,
                GameManager.centerY * 1.3, buttonWidth, GameManager.centerY / 2,
                "HIGH SCORE", "PLAY", "EXIT");
        this.getComponents().add(this.screenController);
    }

    /**
     *
     */
    @Override
    public void prepare() {
        Game.audio().playMusic(TITLE_THEME);

        this.screenController.setEnabled(true);
        super.prepare();
        this.screenController.fixMenuView();

        Game.loop().attach(this);
        Game.graphics().setBaseRenderScale(6f * Game.window().getResolutionScale());
    }

    /**
     * @param g The graphics object to render on.
     */
    @Override
    public void render(final Graphics2D g) {
        final BufferedImage BG = Resources.images().get("src/main/resources/MenuView/bg.png");

        renderClouds(g);
        ImageRenderer.render(g, BG, 0, 0);

        g.setFont(GameManager.PIXELED_BIG);
        g.setColor(Color.BLACK);
        TextRenderer.renderWithOutline(g, "CHALMERSFORCE",
                GameManager.centerX - (13 * 64) / 2.0, 200, Color.WHITE);
        g.setFont(GameManager.RAINY_MEDIUM);
        TextRenderer.renderWithOutline(g, "© 2019 by Mystery Inc.",
                GameManager.centerX - (13 * 64) / 2.0, 250, Color.WHITE);

        super.render(g);
    }

    public static void showHighScore() {
        Game.audio().playSound(GameManager.SELECT_SOUND);
        Game.window().getRenderComponent().fadeOut(500);

        Game.loop().perform(500, () -> {
            Game.window().getRenderComponent().fadeIn(500);
            Game.screens().display("HighScore");
        });
    }

    public static void startGame() {
        Game.audio().playSound(GameManager.SELECT_SOUND);
        Game.window().getRenderComponent().fadeOut(2500);
        Game.audio().fadeMusic(250);

        // Display Help Screen
        Game.loop().perform(3500, () -> {
            Game.window().getRenderComponent().fadeIn(1000);
            Game.screens().display("Help");
        });
    }

    public static void exit() {
        Game.audio().playSound(GameManager.SELECT_SOUND);
        System.exit(0);
    }

    /**
     *
     */
    @Override
    public void suspend() {
        super.suspend();
        Game.loop().detach(this);
        //Game.audio().stopMusic();
    }

    /**
     * This method is called by the game loop on all objects that need to update their attributes.
     */
    @Override
    public void update() {

    }

    // This is kind of ugly and only works for like, a few minutes, but at least it works. Kind of.
    private void renderClouds(Graphics2D g) {
        final BufferedImage CLOUDS = Resources.images().get("src/main/resources/MenuView/clouds.png");
        final double cloudSpeed = 0.3;

        for (int offset = 0; offset < 10000; offset += 1279) {
            ImageRenderer.render(g, CLOUDS, Game.time().now() * cloudSpeed % (CLOUDS.getWidth() + Game.window().getResolution().getWidth()) - offset, 0);
        }
    }
}
