package View;

import Controller.ScreenController;
import Controller.SelectionController;
import Model.GameManager;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Lets the player choose a name, a character and a difficulty level.
 *
 * @author Emma Pettersson
 */
public class SelectionView extends Screen implements IUpdateable {
    SelectionController selectionController;

    public TextField nameField;

    // Temporary
        final BufferedImage CH1 = Resources.images().get("src/main/resources/SelectionView/ch1.png");
        final BufferedImage CH2 = Resources.images().get("src/main/resources/SelectionView/ch2.png");
        final BufferedImage CH3 = Resources.images().get("src/main/resources/SelectionView/ch3.png");
        final BufferedImage CH4 = Resources.images().get("src/main/resources/SelectionView/ch4.png");
        final BufferedImage CH5 = Resources.images().get("src/main/resources/SelectionView/ch5.png");

        final BufferedImage P1 = Resources.images().get("src/main/resources/SelectionView/p1.png");
        final BufferedImage P2 = Resources.images().get("src/main/resources/SelectionView/p2.png");
        final BufferedImage P3 = Resources.images().get("src/main/resources/SelectionView/p3.png");
        final BufferedImage P4 = Resources.images().get("src/main/resources/SelectionView/p4.png");
        final BufferedImage P5 = Resources.images().get("src/main/resources/SelectionView/p5.png");

    /**
     * @param screenName
     *      Name of the screen.
     */
    public SelectionView(String screenName) {
        super(screenName);
    }

    @Override
    protected void initializeComponents() {
        nameField = new TextField("");
    }

    @Override
    public void prepare() {
        super.prepare();

        Game.loop().attach(this);
    }

    /**
     * @param g
     *      The graphics object to render on.
     */
    @Override
    public void render(final Graphics2D g) {
        final BufferedImage BG = Resources.images().get("src/main/resources/SelectionView/bg.png");

        renderMovingBG(g);
        ImageRenderer.render(g, BG, 0, 0);

        g.setFont(GameManager.PIXELED_MEDIUM);
        g.setColor(Color.WHITE);
        TextRenderer.renderWithOutline(g, "Who are you?", GameManager.centerX - (12 * 40) / 2.0, 100, Color.BLACK);

        renderCharacterPortraits(g);

        super.render(g);
    }

    private void renderCharacterPortraits(final Graphics2D g) {
        ImageRenderer.render(g, P1, 295, 150);
        ImageRenderer.render(g, P2, 445, 150);
        ImageRenderer.render(g, P3, GameManager.centerX - (P3.getWidth() / 2.0), 150);
        ImageRenderer.render(g, P4, 745, 150);
        ImageRenderer.render(g, P5, 895, 150);
    }

    private void renderChosenCharacter(final Graphics2D g, String characterID) {

    }

    // This is kind of ugly and only works for like, a few minutes, but at least it works. Kind of.
    private void renderMovingBG(Graphics2D g) {
        final BufferedImage CLOUDS = Resources.images().get("src/main/resources/SelectionView/bg_clouds.png");
        final BufferedImage WATER = Resources.images().get("src/main/resources/SelectionView/bg_water.png");
        final double cloudSpeed = 0.3;
        final double waterSpeed = 0.1;

        for (int offset = 0; offset < 10000; offset += 1279) {
            ImageRenderer.render(g, CLOUDS, Game.time().now() * cloudSpeed % (CLOUDS.getWidth() + Game.window().getResolution().getWidth()) - offset, 0);
            ImageRenderer.render(g, WATER, Game.time().now() * waterSpeed % (CLOUDS.getWidth() + Game.window().getResolution().getWidth()) - offset, 0);
        }
    }

    /**
     * No idea what this does tbh
     */
    @Override
    public void update() {

    }
}
