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
 * Renders different components depending on the current SelectionState.
 *
 * @author Emma Pettersson
 * @version 0.1
 */
public class SelectionView extends Screen implements IUpdateable {
    private EnterNameComponent enterNameComponent;
    private ChooseCharacterComponent chooseCharacterComponent;
    private ChooseLevelComponent chooseLevelComponent;

    private static SelectionController selectionController;

    /**
     * Constructor for the SelectionView
     *
     * @param screenName name of the screen.
     */
    public SelectionView(String screenName) {
        super(screenName);
    }

    @Override
    protected void initializeComponents() {
        selectionController = new SelectionController();
        this.enterNameComponent = new EnterNameComponent();
        this.chooseCharacterComponent = new ChooseCharacterComponent();
        this.chooseLevelComponent = new ChooseLevelComponent();

        this.getComponents().add(this.enterNameComponent);
        this.getComponents().add(selectionController);
    }

    /**
     * Prepare the GuiComponent and all its child Components
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
     * @param g the graphics object to render on.
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
            ImageRenderer.render(g, CLOUDS, Game.time().now() * cloudSpeed % (CLOUDS.getWidth() +
                    Game.window().getResolution().getWidth()) - offset, 0);
            ImageRenderer.render(g, WATER, Game.time().now() * waterSpeed % (CLOUDS.getWidth() +
                    Game.window().getResolution().getWidth()) - offset, 0);
        }
    }

    /**
     * Method called by the game loop on all objects that need to update their attributes.
     */
    @Override
    public void update() {
        switch (selectionController.state) {
            case ENTER_NAME:
                if (!this.getComponents().contains(this.enterNameComponent)) {
                    EnterNameComponent.enterName.setSelected(true);
                    EnterNameComponent.enterName.setEnabled(true);

                    this.getComponents().remove(this.chooseCharacterComponent);
                    this.getComponents().add(this.enterNameComponent);
                }
                break;
            case CHOOSE_CHARACTER:
                if (!this.getComponents().contains(this.chooseCharacterComponent)) {
                    Game.audio().playSound(GameManager.SELECT_SOUND);
                    selectionController.setPlayerName(EnterNameComponent.enterName.getText());
                    EnterNameComponent.enterName.setSelected(false);
                    EnterNameComponent.enterName.setEnabled(false);

                    this.getComponents().remove(this.enterNameComponent);
                    this.getComponents().remove(this.chooseLevelComponent);
                    this.getComponents().remove(EnterNameComponent.enterName);
                    this.getComponents().add(this.chooseCharacterComponent);
                }

                break;
            case CHOOSE_LEVEL:
                if (!this.getComponents().contains(this.chooseLevelComponent)) {
                    Game.audio().playSound(GameManager.SELECT_SOUND);
                    selectionController.setPlayerCharacter();

                    this.getComponents().remove(this.chooseCharacterComponent);
                    this.getComponents().add(this.chooseLevelComponent);
                }

                break;
            case GAME_START:
                this.getComponents().clear();
                this.suspend();
                selectionController.state = SelectionController.SelectionState.GAME_STARTED;

                Game.audio().playSound(GameManager.SELECT_SOUND);
                Game.window().getRenderComponent().fadeOut(1500);
                Game.audio().fadeMusic(150);

                Game.loop().perform(2500, () -> {
                    Game.window().getRenderComponent().fadeIn(1000);
                    Game.screens().display("Game");
                    Game.graphics().setBaseRenderScale(2.001f);
                    GameManager.setState(GameManager.GameState.INGAME);
                });

                break;
            case GAME_STARTED:
                break;
        }
    }

    /**
     * Embedded class for an EnterNameComponent.
     * Lets the player enter their name.
     *
     * @author Emma Pettersson
     * @version 0.1
     */
    public static class EnterNameComponent extends GuiComponent {
        public static TextFieldComponent enterName;

        EnterNameComponent() {
            super(0, 0, Game.window().getResolution().getWidth(), Game.window().getResolution().getHeight());

            enterName = new TextFieldComponent(GameManager.centerX - (500 / 2.0), 200, 500, 100,
                    null, "");
            this.getComponents().add(enterName);
        }

        /**
         * @param g The graphics object to render on.
         */
        @Override
        public void render(Graphics2D g) {
            renderHeader(g);
            renderTextField();

            super.render(g);
        }

        private void renderHeader(Graphics2D g) {
            String text = "Enter Your Name";
            g.setFont(GameManager.PIXELED_MEDIUM);
            g.setColor(Color.WHITE);
            TextRenderer.renderWithOutline(g, text,
                    GameManager.centerX - (text.length() * g.getFont().getSize()) / 2.0, 100, Color.BLACK);

            text = "Confirm by pressing enter";
            g.setFont(GameManager.PIXELED_XSMALL);
            g.setColor(Color.BLACK);
            TextRenderer.renderWithOutline(g, text,
                    GameManager.centerX - (text.length() * g.getFont().getSize()) / 2.0, 135, Color.WHITE);
        }

        private void renderTextField() {
            enterName.getAppearance().setBackgroundColor1(Color.WHITE);
            enterName.getAppearance().setTransparentBackground(false);
            enterName.setFont(GameManager.RAINY_MEDIUM);
            enterName.getAppearance().setForeColor(Color.BLACK);
            enterName.setTextAlign(Align.LEFT);
            enterName.setSelected(true);
        }
    }

    /**
     * Embedded class for a ChooseCharacterComponent.
     * Lets the player choose a Character and displays the Character's name and stats.
     *
     * @author Emma Pettersson
     * @version 0.1
     */
    public static class ChooseCharacterComponent extends GuiComponent {
        ChooseCharacterComponent() {
            super(0, 0, Game.window().getResolution().getWidth(), Game.window().getResolution().getHeight());
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
            TextRenderer.renderWithOutline(g, text,
                    GameManager.centerX - (text.length() * g.getFont().getSize()) / 2.0, 100, Color.BLACK);
        }

        private void renderCharacterPortraits(final Graphics2D g) {
            int i  = 295;

            for (GameManager.Character character : GameManager.Character.values()) {
                if (selectionController.selectedChar == character) {
                    g.setColor(Color.WHITE);
                    g.fillRect(i - 5, 145, 100, 96);
                }

                ImageRenderer.render(g, character.getCharacterPortrait(), i, 150);
                i += 150;
            }
        }

        private void renderChosenCharacter(final Graphics2D g) {
            GameManager.Character character = selectionController.selectedChar;

            ImageRenderer.renderScaled(g, character.getSprite(),
                    GameManager.centerX - (character.getSprite().getWidth()) / 2.0, 300, 0.75);

            g.setFont(GameManager.PIXELED_SMALL);
            g.setColor(Color.BLACK);
            TextRenderer.renderWithOutline(g, character.name(),
                    GameManager.centerX - (character.name().length() * g.getFont().getSize() / 2.0),290,
                    Color.WHITE);

            g.setFont(GameManager.PIXELED_XSMALL);
            TextRenderer.render(g, "  HP:", 720, 350);
            TextRenderer.render(g, "DEF:", 720, 380);
            TextRenderer.render(g, "STR:", 720, 410);

            g.setColor(Color.WHITE);
            TextRenderer.render(g, Integer.toString(character.getHp()), 780, 350);
            TextRenderer.render(g, Integer.toString(character.getDef()), 780, 380);
            TextRenderer.render(g, Integer.toString(character.getStr()), 780, 410);

            // TODO render weapon (and hat?)
        }
    }

    /**
     * Embedded class for a ChooseLevelComponent.
     * Lets the player choose a difficulty level and displays its description.
     *
     * @author Emma Pettersson
     * @version 0.1
     */
    public static class ChooseLevelComponent extends GuiComponent {
        ChooseLevelComponent() {
            super(0, 0, Game.window().getResolution().getWidth(), Game.window().getResolution().getHeight());
        }

        /**
         * Renders the header text and the difficulty choices.
         *
         * @param g The graphics object to render on.
         */
        @Override
        public void render(Graphics2D g) {
            renderHeader(g);
            renderChoices(g);

            super.render(g);
        }

        private void renderHeader(Graphics2D g) {
            String text = "Choose Your Difficulty Level";

            g.setFont(GameManager.PIXELED_MEDIUM);
            g.setColor(Color.WHITE);
            TextRenderer.renderWithOutline(g, text,
                    GameManager.centerX - (text.length() * g.getFont().getSize()) / 2.0 + 100, 100, Color.BLACK);
        }

        private void renderChoices(Graphics2D g) {
            // This array (and the iteration variable i) is needed to place the text in the proper places.
            double[] placement = new double[] {200, 550, 950};
            int i  = 0;

            for (GameManager.DifficultyLevel difficultyLevel : GameManager.DifficultyLevel.values()) {
                g.setFont(GameManager.PIXELED_MEDIUM);

                if (difficultyLevel == GameManager.getSelectedDifficulty()) {
                    g.setColor(Color.BLACK);
                    TextRenderer.render(g, difficultyLevel.name(), placement[i], 350);

                    g.setFont(GameManager.RAINY_MEDIUM);
                    TextRenderer.render(g, difficultyLevel.getDescription(), GameManager.centerX - 300, 445);
                } else {
                    g.setColor(Color.WHITE);
                    TextRenderer.render(g, difficultyLevel.name(), placement[i], 350);
                }

                i ++;
            }
        }
    }
}