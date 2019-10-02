package Controller;

import View.SelectionView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Emma Pettersson
 */
public class SelectionController {
    public String name;
    private SelectionView selectionView;

    public void setPlayerName(String name) {
        this.name = name;

        // Has to be saved somewhere with the current score, so it can be used for the highscore list later
    }

    public void setPlayerCharacter() {
        // Set HP, DEF, STR, sprite..?
    }

    public void setDifficultyLevel() {
        // Easy, Medium, or Hard - Load the correct level
    }
}
