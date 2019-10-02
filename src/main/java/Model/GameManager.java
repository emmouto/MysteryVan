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
    public static final Font PIXELED_BIG = Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",64f);
    public static final Font PIXELED_MEDIUM = Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",40f);
    public static final Font PIXELED_SMALL = Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",24f);
    public static final Font PIXELED_XSMALL = Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",12f);
    public static final Font RAINY_MEDIUM = Resources.fonts().get("src/main/resources/fonts/rainyhearts.ttf",50f);
    public static final Font RAINY_SMALL = Resources.fonts().get("src/main/resources/fonts/rainyhearts.ttf",24f);
    public static final Sound SELECT_SOUND = Resources.sounds().get("src/main/resources/audio/sfx/menu_sound.wav");
}
