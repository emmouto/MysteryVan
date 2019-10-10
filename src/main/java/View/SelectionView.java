package View;

import Controller.SelectionController;

import de.gurkenlabs.litiengine.Align;
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
 * Renders different components depending on the current <code>SELECTION_STATE</code>.
 *
 * @author Emma Pettersson
 * @version 0.1
 */
public class SelectionView extends Screen implements IUpdateable {
    private EnterNameComponent enterNameComponent;
    private ChooseCharacterComponent chooseCharacterComponent;
    private ChooseLevelComponent chooseLevelComponent;
    private SelectionController selectionController;

    /**
     * Constructor for the SelectionView
     *
     * @param screenName Name of the screen.
     */
    public SelectionView(String screenName) {
        super(screenName);
    }

    @Override
    protected void initializeComponents() {
        this.selectionController = new SelectionController();
        this.enterNameComponent = new EnterNameComponent();
        this.chooseCharacterComponent = new ChooseCharacterComponent(selectionController);
        this.chooseLevelComponent = new ChooseLevelComponent();

        this.getComponents().add(this.enterNameComponent);
        this.getComponents().add(this.selectionController);
    }

    /**
     * Prepare the <code>GuiComponent</code> and all its child Components
     * (Makes the GuiComponent visible and adds mouse listeners).
     */
    @Override
    public void prepare() {
        super.prepare();

        Game.loop().attach(this);
    }

    /**
     * Renders the background for the SelectionView.
     *
     * @param g The graphics object to render on.
     */
    @Override
    public void render(final Graphics2D g) {
        final BufferedImage BG = Resources.images().get("src/main/resources/SelectionView/bg.png");

        renderMovingBG(g);
        ImageRenderer.render(g, BG, 0, 0);

        super.render(g);

        if (this.getComponents().contains(this.chooseCharacterComponent)) {
            this.chooseCharacterComponent.render(g);
        } else if (this.getComponents().contains(this.chooseLevelComponent)) {
            this.chooseLevelComponent.render(g);
        }
    }


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
     * Method called by the game loop on all objects that need to update their attributes.
     */
    @Override
    public void update() {
        switch (selectionController.state) {
            case CHOOSE_CHARACTER:
                if (!this.getComponents().contains(this.chooseCharacterComponent)) {
                    selectionController.setPlayerName(enterNameComponent.enterName.getText());
                    enterNameComponent.enterName.setSelected(false);
                    enterNameComponent.enterName.setEnabled(false);

                    this.getComponents().remove(this.enterNameComponent);
                    this.getComponents().remove(this.enterNameComponent.enterName);
                    this.getComponents().add(this.chooseCharacterComponent);
                }

                break;
            case CHOOSE_HAT:

                break;
            case CHOOSE_WEAPON:

                break;
            case CHOOSE_LEVEL:
                if (!this.getComponents().contains(this.chooseLevelComponent)) {
                    selectionController.setPlayerCharacter();

                    this.getComponents().remove(this.chooseCharacterComponent);
                    this.getComponents().add(this.chooseLevelComponent);
                }

                break;
            case GAME_START:
                this.getComponents().clear();
                Game.screens().display("Game");
                Game.graphics().setBaseRenderScale(2.001f);
                GameManager.setState(GameManager.GameState.INGAME);
                break;
            default:
                break;
        }
    }

    /**
     * ...
     *
     * @author Emma Pettersson
     * @version 0.1
     */
    public static class EnterNameComponent extends GuiComponent {
        TextFieldComponent enterName;

        EnterNameComponent() {
            super(0, 0, Game.window().getResolution().getWidth(), Game.window().getResolution().getHeight());

            enterName = new TextFieldComponent(GameManager.centerX - (500 / 2.0), 200, 500, 100,
                    null, "");
            this.getComponents().add(this.enterName);
        }

        /**
         * @param g The graphics object to render on.
         */
        @Override
        public void render(Graphics2D g) {
            renderHeader(g);
            renderTextField(g);

            super.render(g);
        }

        private void renderHeader(Graphics2D g) {
            String text = "Enter Your Name";
            g.setFont(GameManager.PIXELED_MEDIUM);
            g.setColor(Color.WHITE);
            TextRenderer.renderWithOutline(g, text, GameManager.centerX - (text.length() * g.getFont().getSize()) / 2.0, 100, Color.BLACK);

            text = "Confirm by pressing enter";
            g.setFont(GameManager.PIXELED_XSMALL);
            g.setColor(Color.BLACK);
            TextRenderer.renderWithOutline(g, text, GameManager.centerX - (text.length() * g.getFont().getSize()) / 2.0, 135, Color.WHITE);
        }

        private void renderTextField(Graphics2D g) {
            enterName.getAppearance().setBackgroundColor1(Color.WHITE);
            enterName.getAppearance().setTransparentBackground(false);
            enterName.setFont(GameManager.RAINY_MEDIUM);
            enterName.getAppearance().setForeColor(Color.BLACK);
            enterName.setTextAlign(Align.CENTER_RIGHT);
            enterName.setSelected(true);
        }
    }

    /**
     * ...
     *
     * @author Emma Pettersson
     * @version 0.1
     */
    public static class ChooseCharacterComponent extends GuiComponent {
        private SelectionController selectionController;

        ChooseCharacterComponent(SelectionController selectionController) {
            super(0, 0, Game.window().getResolution().getWidth(), Game.window().getResolution().getHeight());

            this.selectionController = selectionController;
        }

        /**
         * @param g The graphics object to render on.
         */
        @Override
        public void render(Graphics2D g) {
            renderHeader(g);
            renderCharacterPortraits(g);
            renderChosenCharacter(g);

            super.render(g);
        }

        private void renderHeader(Graphics2D g) {
            String text = "Choose Your Character";

            g.setFont(GameManager.PIXELED_MEDIUM);
            g.setColor(Color.WHITE);
            TextRenderer.renderWithOutline(g, text, GameManager.centerX - (text.length() * g.getFont().getSize()) / 2.0, 100, Color.BLACK);
        }

        private void renderCharacterPortraits(final Graphics2D g) {
            int i  = 295;

            for (CHARACTER character : CHARACTER.values()) {
                if (selectionController.selectedChar == character) {
                    g.setColor(Color.WHITE);
                    g.fillRect(i - 5, 145, 100, 96);
                }

                ImageRenderer.render(g, character.getCharacterPortrait(), i, 150);
                i += 150;
            }
        }

        private void renderChosenCharacter(final Graphics2D g) {
            CHARACTER character = selectionController.selectedChar;

            ImageRenderer.render(g, character.getSprite(),
                    GameManager.centerX - (character.getSprite().getWidth()) / 2.0, 255);
        }
    }

    /**
     * Renders the components necessary for choosing the difficulty level.
     *
     * @author Emma Pettersson
     * @version 0.1
     */
    public static class ChooseLevelComponent extends GuiComponent {
        ChooseLevelComponent() {
            super(0, 0, Game.window().getResolution().getWidth(), Game.window().getResolution().getHeight());
        }

        /**
         * @param g The graphics object to render on.
         */
        @Override
        public void render(Graphics2D g) {
            renderHeader(g);

            super.render(g);
        }

        private void renderHeader(Graphics2D g) {
            String text = "Choose Your Difficulty Level";

            g.setFont(GameManager.PIXELED_MEDIUM);
            g.setColor(Color.WHITE);
            TextRenderer.renderWithOutline(g, text,
                    GameManager.centerX - (text.length() * g.getFont().getSize()) / 2.0 + 100, 100, Color.BLACK);
        }
    }
}