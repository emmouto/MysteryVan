package Model;

/**
 * TODO description
 *
 * @author
 */
public class Weapon {
    private String name;
    private int damage;
    private int range;

    /**
     * TODO description
     *
     * @param name
     *      TODO description
     * @param damage
     *      TODO description
     * @param range
     *      TODO description
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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
