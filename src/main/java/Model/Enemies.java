package Model;


public class Enemies {

    private int HP;
    private Equipment equipment; //Enemies can have a weapon, armor etc that will make them harder to defeat.

    public int getHP() {
        return HP;
    }

    public Equipment getWeapon() {
        return equipment;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setWeapon(Equipment weapon) {
        this.equipment = weapon;
    }
}