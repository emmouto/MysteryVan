package Model;


public class Enemy {

    private int HP;
    private Equipment equipment; //Enemies can have a weapon, armor etc that will make them harder to defeat.

    private int defeatPoints; //number which equals the difficulty of defeating enemy
                            // and how many points the player gets, btw 50-200pts?


    public Enemy(int HP, Equipment equipment, int defeatPoints) {
        this.HP = HP;
        this.equipment = equipment;
        this.defeatPoints = defeatPoints;
    }

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

    public int getDefeatPoints() {
        return defeatPoints;
    }

    public void setDefeatPoints(int defeatPoints) {
        this.defeatPoints = defeatPoints;
    }
}