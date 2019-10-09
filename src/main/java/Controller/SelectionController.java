package Controller;

import Model.CHARACTER;
import Model.Hat;
import Model.Weapon;
import View.SelectionView;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.GuiComponent;

/**
 * Controls for the SelectionView.
 *
 * @author Emma Pettersson
 * @version 0.1
 */
public class SelectionController extends GuiComponent {
    private SelectionView selectionView;
    private PlayerController playerController;
    private CHARACTER selectedChar;

    public SelectionController() {
        super(0, 0, Game.window().getResolution().getWidth(), Game.window().getResolution().getHeight());
    }

    /**
     *
     */
    public enum SELECTION_STATE {
        ENTER_NAME,
        CHOOSE_CHARACTER,
        CHOOSE_WEAPON,
        CHOOSE_HAT,
        CHOOSE_LEVEL
    }

    public SELECTION_STATE state = SELECTION_STATE.ENTER_NAME;


    /**
     * Takes the name entered by the user and sets it as the Player's name.
     *
     * @param name The name entered by the user.
     */
    public void setPlayerName(String name) {
        playerController.playerList.get(0).setName(name);
    }

    /**
     *
     */
    public void setPlayerCharacter() {
        playerController.playerList.get(0).setHP(selectedChar.getHp());
        playerController.playerList.get(0).setStrength(selectedChar.getStr());
        playerController.playerList.get(0).setDefence(selectedChar.getDef());
    }

    /**
     *
     * @param weapon The weapon chosen by the user.
     */
    public void setPlayerWeapon(Weapon weapon) {
        playerController.playerList.get(0).setWeapon(weapon);
    }

    /**
     *
     * @param hat The hat chosen by the user.
     */
    public void setPlayerHat(Hat hat) {
        playerController.playerList.get(0).setHat(hat);
    }

    /**
     *
     */
    public void setDifficultyLevel() {
        // Easy, Medium, or Hard - Load the correct level
    }
}
