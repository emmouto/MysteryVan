package Model;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.sound.Sound;

import java.awt.*;

/**
 * Holds data used in several places. Or something like that.
 *
 * @author Emma Pettersson
 */
public final class GameManager {
    public static final double centerX = Game.window().getResolution().getWidth() / 2.0;
    public static final double centerY = Game.window().getResolution().getHeight() * 1 / 2;

    // Fonts
    public static final Font PIXELED_BIG = Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",64f);
    public static final Font PIXELED_MEDIUM = Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",40f);
    public static final Font PIXELED_SMALL = Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",24f);
    public static final Font PIXELED_XSMALL = Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",12f);
    public static final Font RAINY_MEDIUM = Resources.fonts().get("src/main/resources/fonts/RainyHearts.ttf",50f);
    public static final Font RAINY_SMALL = Resources.fonts().get("src/main/resources/fonts/RainyHearts.ttf",24f);

    // Sounds
    public static final Sound SELECT_SOUND = Resources.sounds().get("src/main/resources/audio/sfx/menu_sound.wav");

    /**
     * The possible states for the game.
     */
    public enum GameState {
        INGAME,
        INGAME_PAUSE,
        DEFEAT_SCREEN,
        TITLE_SCREEN,
        HIGHSCORE_SCREEN,
        HELP_SCREEN,
        SELECTION_SCREEN
    }

    private static GameState state = GameState.TITLE_SCREEN;

    public static GameState getState() {
        return state;
    }

    public static void setState(GameState state) {
        GameManager.state = state;
    }
}
