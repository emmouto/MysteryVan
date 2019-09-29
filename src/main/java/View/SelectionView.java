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
 * Lets the player choose a name, a character and a difficulty level.
 *
 * @author Emma Pettersson
 */
public class SelectionView extends Screen implements IUpdateable {
    private final BufferedImage BG = Resources.images().get("src/main/resources/SelectionView/selection_bg.png");

    final BufferedImage CH1 = Resources.images().get("src/main/resources/SelectionView/ch1.png");
    final BufferedImage CH2 = Resources.images().get("src/main/resources/SelectionView/ch2.png");
    final BufferedImage CH3 = Resources.images().get("src/main/resources/SelectionView/ch3.png");
    final BufferedImage CH4 = Resources.images().get("src/main/resources/SelectionView/ch4.png");
    final BufferedImage CH5 = Resources.images().get("src/main/resources/SelectionView/ch5.png");

    final BufferedImage p1 = Resources.images().get("src/main/resources/SelectionView/p1.png");
    final BufferedImage p2 = Resources.images().get("src/main/resources/SelectionView/p2.png");
    final BufferedImage p3 = Resources.images().get("src/main/resources/SelectionView/p3.png");
    final BufferedImage p4 = Resources.images().get("src/main/resources/SelectionView/p4.png");
    final BufferedImage p5 = Resources.images().get("src/main/resources/SelectionView/p5.png");

    /**
     * @param screenName
     *      Name of the screen.
     */
    public SelectionView(String screenName) {
        super(screenName);
    }

    @Override
    protected void initializeComponents() {

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
        ImageRenderer.render(g, BG, 0, 0);

        g.setFont(ScreenController.PIXELED_MEDIUM);
        g.setColor(Color.WHITE);
        TextRenderer.renderWithOutline(g, "Who are you?", ScreenController.centerX - (12 * 40) / 2.0, 100, Color.BLACK);

        renderCharacterPortraits(g);

        super.render(g);
    }

    private void renderCharacterPortraits(final Graphics2D g) {
        ImageRenderer.render(g, p1, 300, 150);
    }

    private void renderChosenCharacter(final Graphics2D g, String characterID) {

    }

    @Override
    public void update() {

    }
}
