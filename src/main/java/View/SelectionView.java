package View;

import Controller.SelectionController;
import Model.CHARACTER;
import Model.GameManager;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.resources.Resources;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Lets the player choose a name, a character and a difficulty level.
 *
 * @author Emma Pettersson
 */
public class SelectionView extends Screen implements IUpdateable, Observable {
    private String screenName;

    SelectionController selectionController;

    public TextField nameField;

    /**
     * @param screenName
     *      Name of the screen.
     */
    public SelectionView(String screenName) {
        super(screenName);
        this.screenName = screenName;
    }

    @Override
    protected void initializeComponents() {
        this.selectionController = new SelectionController(0, 0);
        this.getComponents().add(this.selectionController);

        nameField = new TextField("");
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
     * @param g
     *      The graphics object to render on.
     */
    @Override
    public void render(final Graphics2D g) {
        switch (screenName) {
            case "Selection":
                final BufferedImage BG = Resources.images().get("src/main/resources/SelectionView/bg.png");

                renderMovingBG(g);
                ImageRenderer.render(g, BG, 0, 0);
                break;

            case "Selection_Name":
                enterName(g);
                break;

            case "Selection_Character":
                chooseCharacter(g);
                break;

            case "Selection_Level":

                break;
        }

        super.render(g);
    }

    public void enterName(final Graphics2D g) {
        g.setFont(GameManager.PIXELED_MEDIUM);
        g.setColor(Color.WHITE);
        TextRenderer.renderWithOutline(g, "Enter your name", GameManager.centerX - (12 * 40) / 2.0,
                100, Color.BLACK);
    }

    public void chooseCharacter(final Graphics2D g) {
        g.setFont(GameManager.PIXELED_MEDIUM);
        g.setColor(Color.WHITE);
        TextRenderer.renderWithOutline(g, "Choose your character", GameManager.centerX - (12 * 40) / 2.0,
                100, Color.BLACK);

        renderCharacterPortraits(g);
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

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }
}
