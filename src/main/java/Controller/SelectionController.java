package Controller;

import Model.CHARACTER;
import Model.Hat;
import Model.Weapon;
import View.SelectionView;

import de.gurkenlabs.litiengine.gui.GuiComponent;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Emma Pettersson
 */
public class SelectionController extends GuiComponent implements Observer {
    private SelectionView selectionView;
    private PlayerController playerController;
    private CHARACTER selectedChar;

    public SelectionController(double x, double y) {
        super(x, y);
    }

    public void enterName() {

    }

    // Set stuff
        public void setPlayerName(String name) {
            playerController.playerList.get(0).setName(name);
        }

        public void setPlayerCharacter() {
            // Set HP, DEF, STR, sprite..?
            playerController.playerList.get(0).setHP(selectedChar.getHp());
            playerController.playerList.get(0).setStrength(selectedChar.getStr());
            playerController.playerList.get(0).setDefence(selectedChar.getDef());
        }

        public void setPlayerWeapon(Weapon weapon) {
            playerController.playerList.get(0).setWeapon(weapon);
        }

        public void setPlayerHat(Hat hat) {
            playerController.playerList.get(0).setHat(hat);
        }

        public void setDifficultyLevel() {
            // Easy, Medium, or Hard - Load the correct level
        }

    @Override
    public void update(Observable o, Object arg) {

    }
}
