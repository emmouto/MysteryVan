package Controller;

import Model.Key;
import View.GameManager;
import View.SelectionView;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.gurkenlabs.litiengine.input.Input;

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
     * Also initializes the controls for the controller.
     */
    public SelectionController() {
        super(0, 0, Game.window().getResolution().getWidth(), Game.window().getResolution().getHeight());

        this.selectedChar = GameManager.Character.EMMA;
        this.state = SelectionState.ENTER_NAME;

        initControls();
    }

    private void initControls() {
        Input.keyboard().onKeyReleased(e -> {
            if (Key.enter.isDown) {
                if (this.inputIsLocked()) {
                    return;
                }

                lastInput = Game.time().now();

                this.confirm();
            }
        });

        Input.keyboard().onKeyReleased(e -> {
            if (Key.back.isDown) {
                if (this.inputIsLocked()) {
                    return;
                }

                lastInput = Game.time().now();

                this.back();
            }
        });

        Input.keyboard().onKeyPressed(e -> {
            if (Key.left.isDown) {
                if (this.inputIsLocked()) {
                    return;
                }

                lastInput = Game.time().now();

                this.left();
            }
        });

        Input.keyboard().onKeyPressed(e -> {
            if (Key.right.isDown) {
                if (this.inputIsLocked()) {
                    return;
                }

                lastInput = Game.time().now();

                this.right();
            }
        });
    }

    private void confirm() {
        switch (state) {
            case ENTER_NAME:
                if (!SelectionView.EnterNameComponent.enterName.getText().equals("")) {
                    state = SelectionState.CHOOSE_CHARACTER;
                }
                break;
            case CHOOSE_CHARACTER:
                state = SelectionState.CHOOSE_LEVEL;
                break;
            case CHOOSE_LEVEL:
                state = SelectionState.GAME_START;
                break;
        }
    }

    private void back() {
        switch (state) {
            case ENTER_NAME:

                break;
            case CHOOSE_CHARACTER:
                state = SelectionState.ENTER_NAME;
                break;
            case CHOOSE_LEVEL:
                state = SelectionState.CHOOSE_CHARACTER;

        }
    }

    private void left() {
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
                case MEDIUM:
                    GameManager.setSelectedDifficulty(GameManager.DifficultyLevel.EASY);
                    break;
                case HARD:
                    GameManager.setSelectedDifficulty(GameManager.DifficultyLevel.MEDIUM);
                    break;
            }
        }
    }

    private void right() {
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
                    GameManager.setSelectedDifficulty(GameManager.DifficultyLevel.MEDIUM);
                    break;
                case MEDIUM:
                    GameManager.setSelectedDifficulty(GameManager.DifficultyLevel.HARD);
                    break;
                case HARD:
                    GameManager.setSelectedDifficulty(GameManager.DifficultyLevel.EASY);
                    break;
            }
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
     * Takes the saved info from the CHARACTER enum and updates the player character with it.
     */
    public void setPlayerCharacter() {
        PlayerController.playerList.get(0).setHP(selectedChar.getHp());
        PlayerController.playerList.get(0).setMaxHP(selectedChar.getHp());
        PlayerController.playerList.get(0).setStrength(selectedChar.getStr());
        PlayerController.playerList.get(0).setDefence(selectedChar.getDef());
        PlayerController.playerList.get(0).setHat(selectedChar.getHat());
        PlayerController.playerList.get(0).setWeapon(selectedChar.getWpn());
    }
}
