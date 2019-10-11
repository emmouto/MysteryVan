package Controller;

import Model.Hat;
import Model.Weapon;
import View.CHARACTER;

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

    public CHARACTER selectedChar;
    public DIFFICULTY_LEVEL selectedDifficulty;
    public SELECTION_STATE state;

    public SelectionController() {
        super(0, 0, Game.window().getResolution().getWidth(), Game.window().getResolution().getHeight());

        this.selectedChar = CHARACTER.EMMA;
        this.selectedDifficulty = DIFFICULTY_LEVEL.MEDIUM;
        this.state = SELECTION_STATE.ENTER_NAME;

        initControls();
    }

    private void initControls() {
        Input.keyboard().onKeyReleased(e -> {
            if (e.getKeyCode() == KeyEvent.VK_ENTER ) {
                if (this.inputIsLocked()) {
                    return;
                }

                this.confirm();
                lastInput = Game.time().now();
            }
        });

        Input.keyboard().onKeyPressed(e -> {
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                if (this.inputIsLocked()) {
                    return;
                }

                lastInput = Game.time().now();

                if (state == SELECTION_STATE.CHOOSE_CHARACTER) {
                    switch (selectedChar) {
                        case ADAM:
                            selectedChar = CHARACTER.JONATHAN;
                            break;
                        case ANTONIA:
                            selectedChar = CHARACTER.ADAM;
                            break;
                        case EMMA:
                            selectedChar = CHARACTER.ANTONIA;
                            break;
                        case JENNIFER:
                            selectedChar = CHARACTER.EMMA;
                            break;
                        case JONATHAN:
                            selectedChar = CHARACTER.JENNIFER;
                            break;
                    }
                } else if (state == SELECTION_STATE.CHOOSE_LEVEL) {
                    switch (selectedDifficulty) {
                        case EASY:
                            selectedDifficulty = DIFFICULTY_LEVEL.HARD;
                            break;
                        case MEDIUM:
                            selectedDifficulty = DIFFICULTY_LEVEL.EASY;
                            break;
                        case HARD:
                            selectedDifficulty = DIFFICULTY_LEVEL.MEDIUM;
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

                if (state == SELECTION_STATE.CHOOSE_CHARACTER) {
                    switch (selectedChar) {
                        case ADAM:
                            selectedChar = CHARACTER.ANTONIA;
                            break;
                        case ANTONIA:
                            selectedChar = CHARACTER.EMMA;
                            break;
                        case EMMA:
                            selectedChar = CHARACTER.JENNIFER;
                            break;
                        case JENNIFER:
                            selectedChar = CHARACTER.JONATHAN;
                            break;
                        case JONATHAN:
                            selectedChar = CHARACTER.ADAM;
                    }
                } else if (state == SELECTION_STATE.CHOOSE_LEVEL) {
                    switch (selectedDifficulty) {
                        case EASY:
                            selectedDifficulty = DIFFICULTY_LEVEL.MEDIUM;
                            break;
                        case MEDIUM:
                            selectedDifficulty = DIFFICULTY_LEVEL.HARD;
                            break;
                        case HARD:
                            selectedDifficulty = DIFFICULTY_LEVEL.EASY;
                            break;
                    }
                }
            }
        });
    }

    private void confirm() {
        // TODO insert hat and weapon states
        switch (state) {
            case ENTER_NAME:
                state = SELECTION_STATE.CHOOSE_CHARACTER;
                break;
            case CHOOSE_CHARACTER:
                state = SELECTION_STATE.CHOOSE_LEVEL;
                break;
            case CHOOSE_LEVEL:
                state = SELECTION_STATE.GAME_START;
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
     *
     */
    public enum SELECTION_STATE {
        ENTER_NAME,
        CHOOSE_CHARACTER,
        CHOOSE_WEAPON,
        CHOOSE_HAT,
        CHOOSE_LEVEL,
        GAME_START
    }

    /**
     *
     */
    public enum DIFFICULTY_LEVEL {
        EASY,
        MEDIUM,
        HARD
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
    }

    /**
     * @param weapon The weapon chosen by the user.
     */
    public void setPlayerWeapon(Weapon weapon) {
        PlayerController.playerList.get(0).setWeapon(weapon);
    }

    /**
     * @param hat The hat chosen by the user.
     */
    public void setPlayerHat(Hat hat) {
        PlayerController.playerList.get(0).setHat(hat);
    }

    /**
     * Takes the players chosen difficulty level and loads the correct map.
     */
    public void setDifficultyLevel() {
        // Easy, Medium, or Hard - Load the correct level
    }
}
