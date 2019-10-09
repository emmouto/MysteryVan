package View;

import Controller.SelectionController;
import Model.CHARACTER;
import Model.GameManager;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.gurkenlabs.litiengine.gui.TextFieldComponent;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Lets the player choose a name, a character and a difficulty level.
 *
 * @author Emma Pettersson
 * @version 0.1
 */
public class SelectionView extends Screen implements IUpdateable {
    private enterNameComponent enterNameComponent;
    SelectionController selectionController;

    /**
     * @param screenName Name of the screen.
     */
    public SelectionView(String screenName) {
        super(screenName);

        this.enterNameComponent = new enterNameComponent();
        this.getComponents().add(this.enterNameComponent);
    }

    @Override
    protected void initializeComponents() {

    }

    /**
     * Prepare the GuiComponent and all its child Components
     * (Makes the GuiComponent visible and adds mouse listeners.).
     */
    @Override
    public void prepare() {
        super.prepare();

        Game.loop().attach(this);
    }

    /**
     * @param g The graphics object to render on.
     */
    @Override
    public void render(final Graphics2D g) {
        final BufferedImage BG = Resources.images().get("src/main/resources/SelectionView/bg.png");

        renderMovingBG(g);
        ImageRenderer.render(g, BG, 0, 0);

        super.render(g);
    }

    public void chooseCharacter(final Graphics2D g) {
        String headerText = "Choose your character";

        g.setFont(GameManager.PIXELED_MEDIUM);
        g.setColor(Color.WHITE);
        TextRenderer.renderWithOutline(g, headerText, GameManager.centerX - (headerText.length() * g.getFont().getSize()) / 2.0, 100, Color.BLACK);

        renderCharacterPortraits(g);

        super.render(g);
    }

    private void renderCharacterPortraits(final Graphics2D g) {
        ImageRenderer.render(g, CHARACTER.ADAM.getCharacterPortrait(), 295, 150);
        ImageRenderer.render(g, CHARACTER.ANTONIA.getCharacterPortrait(), 445, 150);
        ImageRenderer.render(g, CHARACTER.EMMA.getCharacterPortrait(), 600, 150);
        ImageRenderer.render(g, CHARACTER.JENNIFER.getCharacterPortrait(), 745, 150);
        ImageRenderer.render(g, CHARACTER.JONATHAN.getCharacterPortrait(), 895, 150);
    }

    private void renderChosenCharacter(final Graphics2D g, CHARACTER character) {

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
     * This method is called by the game loop on all objects that need to update their attributes.
     */
    @Override
    public void update() {

    }

    /**
     * ...
     *
     * @author Emma Pettersson
     * @version 0.1
     */
    public static class enterNameComponent extends GuiComponent {
        TextFieldComponent enterName;

        enterNameComponent() {
            super(0, 0, Game.window().getResolution().getWidth(), Game.window().getResolution().getHeight());

            enterName = new TextFieldComponent(GameManager.centerX - (300 / 2.0), 200, 300, 100, null, "|");
            this.getComponents().add(this.enterName);
        }

        /**
         *
         * @param g The graphics object to render on.
         */
        @Override
        public void render(Graphics2D g) {
            renderHeader(g);
            renderTextField(g);

            super.render(g);
        }

        private void renderHeader(Graphics2D g) {
            String headerText = "Enter your name";

            g.setFont(GameManager.PIXELED_MEDIUM);
            g.setColor(Color.WHITE);
            TextRenderer.renderWithOutline(g, headerText, GameManager.centerX - (headerText.length() * g.getFont().getSize()) / 2.0, 100, Color.BLACK);
        }

        private void renderTextField(Graphics2D g) {
            enterName.setFont(GameManager.RAINY_MEDIUM);
            enterName.getAppearance().setForeColor(Color.BLACK);
        }
    }
}