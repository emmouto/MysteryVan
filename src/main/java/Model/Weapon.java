package Model;

/**
 * Class for creating the weapons that the players can use.
 *
 * @author Antonia Welzel
 * @author Jonathan Carbol
 * @version 0.1
 */
public class Weapon {
    private String name;
    private int damage;
    private int range;

    /**
     * Creates a new Weapon and sets its properties.
     *
     * @param name the name of the Weapon.
     * @param damage the damage that the Weapon does.
     * @param range the range that the Weapon has.
     */
    public Weapon(String name, int damage, int range) {
        this.name = name;
        this.damage = damage;
        this.range = range;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
