package View;

import Controller.GameController;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.sound.Sound;

import java.awt.*;

/**
 * Holds data used in several places. Or something like that.
 *
 * @author Emma Pettersson
 * @version 0.1
 */
public final class GameManager {
    static final double centerX = Game.window().getResolution().getWidth() / 2.0;
    static final double centerY = Game.window().getResolution().getHeight() * 1 / 2;

    // Fonts
    static final Font PIXELED_BIG = Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",64f);
    static final Font PIXELED_MEDIUM = Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",40f);
    public static final Font PIXELED_SMALL = Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",24f);
    static final Font PIXELED_XSMALL = Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",12f);
    static final Font RAINY_MEDIUM = Resources.fonts().get("src/main/resources/fonts/RainyHearts.ttf",50f);
    public static final Font RAINY_SMALL = Resources.fonts().get("src/main/resources/fonts/RainyHearts.ttf",24f);

    // Sounds
    public static final Sound MENU_SOUND = Resources.sounds().get("src/main/resources/audio/sfx/menu_sound.wav");
    public static final Sound SELECT_SOUND = Resources.sounds().get("src/main/resources/audio/sfx/menu_selection.wav");
    static final Sound TITLE_THEME = Resources.sounds().get("src/main/resources/audio/music/title_theme.mp3");
    static final Sound STAGE_1 = Resources.sounds().get("src/main/resources/audio/music/stage1.ogg");
    static final Sound STAGE_2 = Resources.sounds().get("src/main/resources/audio/music/stage2.ogg");
    static final Sound STAGE_3 = Resources.sounds().get("src/main/resources/audio/music/stage3.ogg");

    private static DIFFICULTY_LEVEL selectedDifficulty = DIFFICULTY_LEVEL.NORMAL;
    private static GameState state = GameState.TITLE_SCREEN;

    public static DIFFICULTY_LEVEL getSelectedDifficulty() {
        return selectedDifficulty;
    }

    public static void setSelectedDifficulty(DIFFICULTY_LEVEL selectedDifficulty) {
        GameManager.selectedDifficulty = selectedDifficulty;
    }

    public static GameState getState() {
        return state;
    }

    public static void setState(GameState state) {
        GameManager.state = state;
    }

    /**
     * The possible states for the game.
     */
    public enum GameState {
        INGAME,
        INGAME_PAUSE,
        DEFEAT_SCREEN,
        TITLE_SCREEN,
        HIGH_SCORE_SCREEN,
        HELP_SCREEN,
        SELECTION_SCREEN
    }

    /**
     * Enum containing the different difficulty levels and their descriptions.
     */
    public enum DIFFICULTY_LEVEL {
        EASY    ("The enemy spawn rate is slow."),
        NORMAL  ("The enemy spawn rate is normal."),
        HARD    ("The enemy spawn rate is fast.");

        private String description;

        /**
         * Constructor for a difficulty level.
         *
         * @param description text describing what the difficulty entails.
         */
        DIFFICULTY_LEVEL(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
