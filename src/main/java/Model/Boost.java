package Model;

/**
 * ...
 *
 * @author Antonia Welzel
 * @author Jonathan Carbol
 * @version 0.1
 */
public class Boost {
    //TODO change class to interface or similar? -----#####------

    private int HP;
    private int strength;
    private int defence;

    /**
     * Constructor for a boost.
     *
     * @param HP the added hp the boost gives, if any.
     * @param strength the added strength the boost gives, if any.
     * @param defence the added defence the boost gives, if any.
     */
    public Boost(int HP, int strength, int defence) {
        this.HP = HP;
        this.strength = strength;
        this.defence = defence;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int armour) {
        this.defence = armour;
    }
}
