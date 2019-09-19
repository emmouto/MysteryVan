package Model;

public abstract class Item implements ICollidable {

    // either abstract class or interface

    private int HP;
    private int strength;
    private int defence;

    public int getHP() {
        return HP;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefence() {
        return defence;
    }

    
}


