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

/**
 * The game's title screen.
 *
 * @author Emma Pettersson
 * @version 0.1
 */
public class MenuView extends Screen implements IUpdateable {
    private ScreenController screenController;

    /**
     * Constructor. Sets the name of the screen.
     *
     * @param screenName name of the screen.
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
     * Prepare the GuiComponent and all its child Components.
     * (Makes the GuiComponent visible and adds mouse listeners).
     * This is, for example, done right before switching to a new screen.
     */
    @Override
    public void prepare() {
        Game.audio().playMusic(GameManager.TITLE_THEME);

        this.screenController.setEnabled(true);
        super.prepare();
        this.screenController.fixMenuView();

        Game.loop().attach(this);
        Game.graphics().setBaseRenderScale(6f * Game.window().getResolutionScale());
    }

    /**
     * @param g the graphics object to render on.
     */
    @Override
    public void render(final Graphics2D g) {
        final BufferedImage BG = Resources.images().get("src/main/resources/MenuView/bg.png");

        renderClouds(g);
        ImageRenderer.render(g, BG, 0, 0);

        String text = "CHALMERSFORCE";
        g.setFont(GameManager.PIXELED_BIG);
        g.setColor(Color.BLACK);
        TextRenderer.renderWithOutline(g, text,
                GameManager.centerX - (text.length() * g.getFont().getSize()) / 2.0, 200, Color.WHITE);

        text = "© 2019 by Mystery Inc.";
        g.setFont(GameManager.RAINY_MEDIUM);
        TextRenderer.renderWithOutline(g, text,
                GameManager.centerX - (text.length() * g.getFont().getSize()) / 2.65, 250, Color.WHITE);

        super.render(g);
    }

    /**
     * Suspend the GuiComponent and all its child Components.
     * (Makes the GuiComponent invisible and removes mouse listeners).
     */
    @Override
    public void suspend() {
        super.suspend();
        Game.loop().detach(this);
    }

    /**
     * This method is called by the game loop on all objects that need to update their attributes.
     */
    @Override
    public void update() {

    }

    private void renderClouds(Graphics2D g) {
        final BufferedImage CLOUDS = Resources.images().get("src/main/resources/MenuView/clouds.png");
        final double cloudSpeed = 0.3;

        for (int offset = 0; offset < 10000; offset += 1279) {
            ImageRenderer.render(g, CLOUDS, Game.time().now() * cloudSpeed % (CLOUDS.getWidth() + Game.window().getResolution().getWidth()) - offset, 0);
        }
    }
}
