package Model;

public class Weapon {
    private String name;
    private int DMG;
    private int range;

    public Weapon(String name, int DMG, int range) {
        this.name = name;
        this.DMG = DMG;
        this.range = range;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDMG() {
        return DMG;
    }

    public void setDMG(int DMG) {
        this.DMG = DMG;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
