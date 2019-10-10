package Model;

/**
 * ...
 *
 * @author
 * @version
 */
public class Hat {
    private String name;
    private Boost boost;

    /**
     * ...
     *
     * @param name
     * @param boost
     */
    public Hat(String name, Boost boost) {
        this.name = name;
        this.boost = boost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boost getBoost() {
        return boost;
    }

    public void setBoost(Boost boost) {
        this.boost = boost;
    }
}
