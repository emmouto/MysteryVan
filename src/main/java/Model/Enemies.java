package Model;

public class Enemies {

    private int HP;
    private Equipment weapon;

    public int getHP() {
        return HP;
    }

    public Equipment getWeapon() {
        return weapon;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setWeapon(Equipment weapon) {
        this.weapon = weapon;
    }
}
