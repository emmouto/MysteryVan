package Model;

import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.image.BufferedImage;

/**
 *
 *
 * @author Emma Pettersson
 */
public enum CHARACTER {
    ADAM("Passive-aggressive and undead",15, 20, 10,
            Resources.images().get("src/main/resources/SelectionView/Adam.png"),
            Resources.images().get("src/main/resources/SelectionView/p1.png")),

    ANTONIA("Strong and hungry",20, 25, 20,
            Resources.images().get("src/main/resources/SelectionView/Antonia.png"),
            Resources.images().get("src/main/resources/SelectionView/p2.png")),

    EMMA("Brave and stylish", 20, 15, 10,
            Resources.images().get("src/main/resources/SelectionView/Emma.png"),
            Resources.images().get("src/main/resources/SelectionView/p3.png")),

    JENNIFER("Kind and sporty", 30, 15, 15,
            Resources.images().get("src/main/resources/SelectionView/Jennifer.png"),
            Resources.images().get("src/main/resources/SelectionView/p4.png")),

    JONATHAN("Cute and beardy", 10, 10, 20,
            Resources.images().get("src/main/resources/SelectionView/Jonathan.png"),
            Resources.images().get("src/main/resources/SelectionView/p5.png"));

    private String description;
    private int hp;
    private int str;
    private int def;
    private BufferedImage characterPortrait;
    private BufferedImage sprite;

    CHARACTER(String description, int hp, int str, int def, BufferedImage characterPortrait, BufferedImage sprite) {
        this.description = description;
        this.hp = hp;
        this.str = str;
        this.def = def;
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

    public BufferedImage getCharacterPortrait() {
        return characterPortrait;
    }

    public BufferedImage getSprite() {
        return sprite;
    }
}
