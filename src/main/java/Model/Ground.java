package Model;

public class Ground {

    private String type;
    private boolean dealDamage; // damage when falling into ground, ex. lava but not immediate death
    private boolean instantDeath; // instant death when the grounds are skz/clouds = no ground

    public String getType() {
        return type;
    }

    public boolean getDamage() {
        return dealDamage;
    }

    public boolean getInstantDeath() {
        return instantDeath;
    }
}
