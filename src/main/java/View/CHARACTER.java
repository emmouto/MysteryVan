package View;

import Model.Boost;
import Model.Hat;
import Model.Weapon;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.image.BufferedImage;

/**
 * Enum containing the information about all the playable characters.
 *
 * @author Emma Pettersson
 * @version 0.1
 */
public enum CHARACTER {
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
     * Constructor for a <code>CHARACTER</code>.
     *
     * @param description Short description of the character (flavour text).
     * @param hp The CHARACTER's base health points.
     * @param str The CHARACTER's base strength.
     * @param def The CHARACTER's base defence.
     * @param hat
     * @param wpn
     * @param characterPortrait The CHARACTER's portrait, used when selecting character.
     * @param sprite The CHARACTER's sprite.
     */
    CHARACTER(String description, int hp, int str, int def, Hat hat, Weapon wpn, BufferedImage characterPortrait, BufferedImage sprite) {
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
