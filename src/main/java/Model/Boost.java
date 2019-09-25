package Model;

import javazoom.spi.mpeg.sampled.file.tag.StringableTag;

public class Boost {

    private String name;
    private int HP;
    private int strength;
    private int armour;

    public Boost(String name, int HP, int strength, int armour) {
        this.name = name;
        this.HP = HP;
        this.strength = strength;
        this.armour = armour;
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

    public int getArmour() {
        return armour;
    }

    public void setArmour(int armour) {
        this.armour = armour;
    }
}
