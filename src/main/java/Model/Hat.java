package Model;

/**
 * Class defining a <code>Hat</code>, an item that gives the player a <code>Boost</code>.
 *
 * @author Jennifer Krogh
 * @author Jonathan Carbol
 * @version 0.1
 */
public class Hat {
    private String name;
    private int HP;
    private int strength;
    private int defence;

    /**
     * Creates a new hat and sets the name and <code>Boost</code>.
     *
     * @param name name of the <code>Hat</code>.
     * @param HP the boost the hat gives.
     */
    public Hat(String name, int HP, int strength, int defence) {
        this.name = name;
        this.HP = HP;
        this.strength = strength;
        this.defence = defence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setDefence(int defence) {
        this.defence = defence;
    }
}
