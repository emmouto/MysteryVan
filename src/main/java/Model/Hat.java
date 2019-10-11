package Model;

/**
 * Class defining a <code>Hat</code>, an item that gives the player a <code>Boost</code>.
 *
 * @author Jennifer Krogh
 * @author Jonathan Carbol
 * @version 0.1
 */
public class Hat {
    private String name;
    private Boost boost;

    /**
     * Creates a new hat and sets the name and <code>Boost</code>.
     *
     * @param name name of the <code>Hat</code>.
     * @param boost the boost that the <code>Hat</code> gives.
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
