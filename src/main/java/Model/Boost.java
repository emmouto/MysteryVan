package Model;

public class Boost {


    //TODO change class to interface or similar? -----#####------


    //changed to private
    private String name;
    private int HP;
    private int strength;
    private int defence; // used to be armour


    public Boost(String name, int HP, int strength, int defence) {
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

    public void setDefence(int armour) {
        this.defence = armour;
    }

}
