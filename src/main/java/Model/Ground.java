package Model;

/**
 * Class which handles the different attributes of the ground.
 *
 * @author Antonia Welzel
 * @author Jennifer Krogh
 */
public class Ground implements ICollidable{
    private Type type;
    private boolean dealDamage; // damage when falling into ground, ex. lava but not immediate death
    private boolean instantDeath; // instant death when the grounds are sky/clouds = no ground

    private enum Type {
        GRASS,
        LAVA,
        SKY
    }

    public Ground(Type type, boolean dealDamage, boolean instantDeath) {
        this.type = type;
        this.dealDamage = dealDamage;
        this.instantDeath = instantDeath;
    }

    public Type getType() {
        return type;
    }

    public boolean getDamage() {
        return dealDamage;
    }

    public boolean getInstantDeath() {
        return instantDeath;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setDealDamage(boolean dealDamage) {
        this.dealDamage = dealDamage;
    }

    public void setInstantDeath(boolean instantDeath) {
        this.instantDeath = instantDeath;
    }

    @Override
    public Collider getCollider() {
        return null;
    }
}
