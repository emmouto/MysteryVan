package Model;

public class Boost {

<<<<<<< HEAD
    private int HP;
    private int strength;
    private int defence;
=======
    public String name;
    public int HP;
    public int strength;
    public int armour;

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
>>>>>>> master

    public int getHP() {
        return HP;
    }

<<<<<<< HEAD
=======
    public void setHP(int HP) {
        this.HP = HP;
    }

>>>>>>> master
    public int getStrength() {
        return strength;
    }

<<<<<<< HEAD
    public int getDefence() {
        return defence;
    }


=======
    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getArmour() {
        return armour;
    }

    public void setArmour(int armour) {
        this.armour = armour;
    }
>>>>>>> master
}
