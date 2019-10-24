package View;

import Model.Boost;
import Model.Hat;
import Model.Weapon;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.sound.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Holds data used in several places.
 *
 * @author Emma Pettersson
 * @version 0.1
 */
public final class GameManager {
    static final double centerX = Game.window().getResolution().getWidth() / 2.0;
    static final double centerY = Game.window().getResolution().getHeight() * 1 / 2;

    /* --- Fonts --- */
    static final Font PIXELED_BIG = Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",64f);
    static final Font PIXELED_MEDIUM = Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",40f);
    public static final Font PIXELED_SMALL = Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",24f);
    static final Font PIXELED_XSMALL = Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",12f);
    static final Font RAINY_MEDIUM = Resources.fonts().get("src/main/resources/fonts/RainyHearts.ttf",50f);
    public static final Font RAINY_SMALL = Resources.fonts().get("src/main/resources/fonts/RainyHearts.ttf",24f);

    /* --- Sounds --- */
    public static final Sound MENU_SOUND = Resources.sounds().get("src/main/resources/audio/sfx/menu_sound.wav");
    public static final Sound SELECT_SOUND = Resources.sounds().get("src/main/resources/audio/sfx/menu_selection.wav");
    static final Sound TITLE_THEME = Resources.sounds().get("src/main/resources/audio/music/title_theme.mp3");
    static final Sound STAGE_SELECT =Resources.sounds().get("src/main/resources/audio/music/stage_select.ogg");
    static final Sound STAGE_1 = Resources.sounds().get("src/main/resources/audio/music/stage1.ogg");
    static final Sound STAGE_2 = Resources.sounds().get("src/main/resources/audio/music/stage2.ogg");
    static final Sound STAGE_3 = Resources.sounds().get("src/main/resources/audio/music/stage3.ogg");

    /* --- Enum values -- */
    private static DifficultyLevel selectedDifficulty = DifficultyLevel.NORMAL;
    private static GameState state = GameState.TITLE_SCREEN;

    public static DifficultyLevel getSelectedDifficulty() {
        return selectedDifficulty;
    }

    public static void setSelectedDifficulty(DifficultyLevel selectedDifficulty) {
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
    public enum DifficultyLevel {
        EASY    ("The enemy spawn rate is slow."),
        NORMAL  ("The enemy spawn rate is normal."),
        HARD    ("The enemy spawn rate is fast.");

        private String description;

        /**
         * Constructor for a difficulty level.
         *
         * @param description text describing what the difficulty entails.
         */
        DifficultyLevel(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * Enum containing the information about all the playable characters.
     *
     * @author Emma Pettersson
     * @version 0.1
     */
    public enum Character {
        // TODO fix the stats for the Hats and Weapons
        ADAM    ("Passive-aggressive and undead",15, 20, 10,
                new Hat("Bunny Ears", new Boost(0, 0, 0)),
                new Weapon("Laser Sword", 0, 1),
                Resources.images().get("src/main/resources/SelectionView/Adam_portait.png"),
                Resources.images().get("src/main/resources/SelectionView/Adam.png")),

        ANTONIA ("Strong and hungry",20, 25, 20,
                new Hat("Sunglasses", new Boost(0, 0, 0)),
                new Weapon("Fists", 0, 1),
                Resources.images().get("src/main/resources/SelectionView/Antonia_portait.png"),
                Resources.images().get("src/main/resources/SelectionView/Antonia.png")),

        EMMA    ("Brave and stylish", 20, 15, 10,
                new Hat("Sunhat", new Boost(0, 0, 0)),
                new Weapon("Claws", 0, 1),
                Resources.images().get("src/main/resources/SelectionView/Emma_portait.png"),
                Resources.images().get("src/main/resources/SelectionView/Emma.png")),

        JENNIFER("Kind and sporty", 30, 15, 15,
                new Hat("Horse Mask", new Boost(0, 0, 0)),
                new Weapon("Baseball Bat", 5, 1),
                Resources.images().get("src/main/resources/SelectionView/Jennifer_portait.png"),
                Resources.images().get("src/main/resources/SelectionView/Jennifer.png")),

        JONATHAN("Cute and beardy", 10, 10, 20,
                new Hat("Big Beard", new Boost(0, 0, 10)),
                new Weapon("Moustache Sword", 0, 1),
                Resources.images().get("src/main/resources/SelectionView/Jonathan_portait.png"),
                Resources.images().get("src/main/resources/SelectionView/Jonathan.png"));

        private String description;
        private int hp;
        private int str;
        private int def;
        private Hat hat;
        private Weapon wpn;
        private BufferedImage characterPortrait;
        private BufferedImage sprite; // Temporary - should be changed to String later.

        /**
         * Constructor for a CHARACTER.
         *
         * @param description Short description of the character (flavour text).
         * @param hp The Character's base health points.
         * @param str The Character's base strength.
         * @param def The Character's base defence.
         * @param hat The Character's Hat.
         * @param wpn The Character's Weapon.
         * @param characterPortrait The Character's portrait, used when selecting character.
         * @param sprite The Character's sprite.
         */
        Character(String description, int hp, int str, int def, Hat hat, Weapon wpn, BufferedImage characterPortrait, BufferedImage sprite) {
            this.description = description;
            this.hp = hp;
            this.str = str;
            this.def = def;
            this.hat = hat;
            this.wpn = wpn;
            this.characterPortrait = characterPortrait;
            this.sprite = sprite;
        }

        public String getDescription() {
            return description;
        }

        public int getHp() {
            return hp;
        }

        public int getStr() {
            return str;
        }

        public int getDef() {
            return def;
        }

        public Hat getHat() {
            return hat;
        }

        public Weapon getWpn() {
            return wpn;
        }

        public BufferedImage getCharacterPortrait() {
            return characterPortrait;
        }

        public BufferedImage getSprite() {
            return sprite;
        }
    }
}
