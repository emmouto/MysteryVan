package Model;

/**
 * Class which handles the different attributes of the ground.
 *
 * @author Antonia Welzel
 * @author Jennifer Krogh
 * @author Jonathan Carbol
 * @version 0.1
 */
public class Ground extends Platform implements ICollidable{
    private Type type;
    private boolean dealDamage;
    private boolean instantDeath;

    /**
     * Enum for the ground types.
     */
    enum Type {
        GRASS,
        LAVA,
        SKY
    }

    /**
     * Contructor for the ground class.
     *
     * @param type  The type of ground, different for each level.
     * @param dealDamage    Damage when falling on the ground.
     * @param instantDeath  Instant death when falling on the ground.
     */
    public Ground(int x, int y, int height, int width, Type type, boolean dealDamage, boolean instantDeath) {
        super(x,y,height,width);
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
