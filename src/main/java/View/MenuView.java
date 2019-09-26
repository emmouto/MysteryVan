package View;

import Controller.ScreenController;

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
 */
public class MenuView extends Screen implements IUpdateable {
    private static final Sound SELECT = Resources.sounds().get("src/main/resources/audio/sfx/menu_selection.wav");
    private static final Sound TITLE_THEME = Resources.sounds().get("src/main/resources/audio/music/title_theme.mp3");

    private ScreenController screenController;

    /**
     * @param screenName
     *      Name of the screen.
     */
    public MenuView(String screenName) {
        super(screenName);
    }

    @Override
    protected void initializeComponents() {
        final double buttonWidth = 250;

        this.screenController = new ScreenController(ScreenController.centerX - buttonWidth / 2, ScreenController.centerY * 1.3, buttonWidth, ScreenController.centerY / 2, "HIGHSCORE", "PLAY", "EXIT");
        this.getComponents().add(this.screenController);

        this.screenController.onConfirm(c -> {
            switch (c) {
                case 0:
                    this.showHighscore();
                    break;
                case 1:
                    this.startGame();
                    break;
                case 2:
                    this.exit();
                    break;
                default:
                    break;
            }
        });
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

        this.screenController.incFocus();
    }

    /**
     *
     * @param g
     *      The graphics object to render on.
     */
    @Override
    public void render(final Graphics2D g) {
        final BufferedImage BG = Resources.images().get("src/main/resources/menu/bg.png");

        renderClouds(g);
        ImageRenderer.render(g, BG, 0, 0);

        g.setFont(Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",64f));
        g.setColor(Color.BLACK);
        TextRenderer.renderWithOutline(g, "CHALMERSFORCE", ScreenController.centerX - (13 * 64) / 2.0, 200, Color.WHITE);

        Game.audio().playMusic(Resources.sounds().get("src/main/resources/audio/music/title_theme.mp3"));

        super.render(g);
    }

    private void showHighscore() {
        this.screenController.setEnabled(false);
        Game.audio().playSound(SELECT);

        Game.screens().display("Highscore");
    }

    private void startGame() {
        this.screenController.setEnabled(false);
        Game.audio().playSound(SELECT);
        //Game.window().getRenderComponent().fadeOut(2500); // TODO the fading doesn't work properly, gives black screen
        Game.audio().fadeMusic(250);

        // Stage & Character Selection Screen // TODO implement lol
        Game.loop().perform(2500, () -> {
            Game.audio().stopMusic();
            Game.screens().display("Help");
        });
    }

    private void exit() {
        Game.audio().playSound(SELECT);
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
     *
     */
    @Override
    public void update() {

    }

    // This is kind of ugly and only works for like, a few minutes, but at least it works. Kind of.
    private void renderClouds(Graphics2D g) {
        final BufferedImage CLOUDS = Resources.images().get("src/main/resources/menu/clouds.png");
        final int cloudOffset = 1279;
        final double cloudSpeed = 0.3;

        ImageRenderer.render(g, CLOUDS, Game.time().now() * cloudSpeed % (CLOUDS.getWidth() + Game.window().getResolution().getWidth()), 0);
        ImageRenderer.render(g, CLOUDS, Game.time().now() * cloudSpeed % (CLOUDS.getWidth() + Game.window().getResolution().getWidth()) - cloudOffset, 0);
        ImageRenderer.render(g, CLOUDS, Game.time().now() * cloudSpeed % (CLOUDS.getWidth() + Game.window().getResolution().getWidth()) - cloudOffset * 2, 0);
        ImageRenderer.render(g, CLOUDS, Game.time().now() * cloudSpeed % (CLOUDS.getWidth() + Game.window().getResolution().getWidth()) - cloudOffset * 3, 0);
    }
}
