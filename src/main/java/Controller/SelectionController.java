package Controller;

import View.GameManager;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.gurkenlabs.litiengine.input.Input;

import java.awt.event.KeyEvent;

/**
 * Controls for the SelectionView.
 *
 * @author Emma Pettersson
 * @version 0.1
 */
public class SelectionController extends GuiComponent {
    private static final int DELAY = 180;
    private static long lastInput;

    public GameManager.Character selectedChar;
    public SelectionState state;

    /**
     * Constructor for a new SelectionController.
     * Sets the initial values for the selected character, difficulty, and the starting state.
     * Also inintializes the controls for the Controller.
     */
    public SelectionController() {
        super(0, 0, Game.window().getResolution().getWidth(), Game.window().getResolution().getHeight());

        this.selectedChar = GameManager.Character.EMMA;
        this.state = SelectionState.ENTER_NAME;

        initControls();
    }

    // TODO fix this ugly code
    private void initControls() {
        Input.keyboard().onKeyReleased(e -> {
            if (e.getKeyCode() == KeyEvent.VK_ENTER ) {
                if (this.inputIsLocked()) {
                    return;
                }

                lastInput = Game.time().now();

                this.confirm();
            }
        });

        Input.keyboard().onKeyPressed(e -> {
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                if (this.inputIsLocked()) {
                    return;
                }

                lastInput = Game.time().now();

                if (state == SelectionState.CHOOSE_CHARACTER) {
                    Game.audio().playSound(GameManager.MENU_SOUND);

                    switch (selectedChar) {
                        case ADAM:
                            selectedChar = GameManager.Character.JONATHAN;
                            break;
                        case ANTONIA:
                            selectedChar = GameManager.Character.ADAM;
                            break;
                        case EMMA:
                            selectedChar = GameManager.Character.ANTONIA;
                            break;
                        case JENNIFER:
                            selectedChar = GameManager.Character.EMMA;
                            break;
                        case JONATHAN:
                            selectedChar = GameManager.Character.JENNIFER;
                            break;
                    }
                } else if (state == SelectionState.CHOOSE_LEVEL) {
                    Game.audio().playSound(GameManager.MENU_SOUND);

                    switch (GameManager.getSelectedDifficulty()) {
                        case EASY:
                            GameManager.setSelectedDifficulty(GameManager.DifficultyLevel.HARD);
                            break;
                        case NORMAL:
                            GameManager.setSelectedDifficulty(GameManager.DifficultyLevel.EASY);
                            break;
                        case HARD:
                            GameManager.setSelectedDifficulty(GameManager.DifficultyLevel.NORMAL);
                            break;
                    }
                }
            }
        });

        Input.keyboard().onKeyPressed(e -> {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                if (this.inputIsLocked()) {
                    return;
                }

                lastInput = Game.time().now();

                if (state == SelectionState.CHOOSE_CHARACTER) {
                    Game.audio().playSound(GameManager.MENU_SOUND);

                    switch (selectedChar) {
                        case ADAM:
                            selectedChar = GameManager.Character.ANTONIA;
                            break;
                        case ANTONIA:
                            selectedChar = GameManager.Character.EMMA;
                            break;
                        case EMMA:
                            selectedChar = GameManager.Character.JENNIFER;
                            break;
                        case JENNIFER:
                            selectedChar = GameManager.Character.JONATHAN;
                            break;
                        case JONATHAN:
                            selectedChar = GameManager.Character.ADAM;
                    }
                } else if (state == SelectionState.CHOOSE_LEVEL) {
                    Game.audio().playSound(GameManager.MENU_SOUND);

                    switch (GameManager.getSelectedDifficulty()) {
                        case EASY:
                            GameManager.setSelectedDifficulty(GameManager.DifficultyLevel.NORMAL);
                            break;
                        case NORMAL:
                            GameManager.setSelectedDifficulty(GameManager.DifficultyLevel.HARD);
                            break;
                        case HARD:
                            GameManager.setSelectedDifficulty(GameManager.DifficultyLevel.EASY);
                            break;
                    }
                }
            }
        });
    }

    private void confirm() {
        switch (state) {
            case ENTER_NAME:
                state = SelectionState.CHOOSE_CHARACTER;
                break;
            case CHOOSE_CHARACTER:
                state = SelectionState.CHOOSE_LEVEL;
                break;
            case CHOOSE_LEVEL:
                state = SelectionState.GAME_START;
                break;
        }
    }

    private boolean inputIsLocked() {
        if (this.isSuspended() || !this.isVisible() || !this.isEnabled()) {
            return true;
        }

        return Game.time().since(lastInput) < DELAY;
    }

    /**
     * Enum containing the different states for the Selection screen.
     */
    public enum SelectionState {
        ENTER_NAME,
        CHOOSE_CHARACTER,
        CHOOSE_LEVEL,
        GAME_START,
        GAME_STARTED
    }

    /**
     * Takes the name entered by the user and sets it as the Player's name.
     *
     * @param name The name entered by the user.
     */
    public void setPlayerName(String name) {
        PlayerController.playerList.get(0).setName(name);
    }

    /**
     * Takes the saved info from the <code>CHARACTER</code> enum and updates the player character with it.
     */
    public void setPlayerCharacter() {
        PlayerController.playerList.get(0).setHP(selectedChar.getHp());
        PlayerController.playerList.get(0).setMaxHP(selectedChar.getHp());
        PlayerController.playerList.get(0).setStrength(selectedChar.getStr());
        PlayerController.playerList.get(0).setDefence(selectedChar.getDef());
        PlayerController.playerList.get(0).setHat(selectedChar.getHat());
        PlayerController.playerList.get(0).setWeapon(selectedChar.getWpn());
    }

    /**
     * Takes the players chosen difficulty level and loads the correct map.
     */
    public void setDifficultyLevel() {
        // Easy, Medium, or Hard - Load the correct level
        // TODO implement this (when different levels/maps exist)
    }
}
