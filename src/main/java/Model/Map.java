package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Map class for the project. It contains a list of platforms that are used to check player/enemy movements such as gravity.
 *
 * @author Jonathan Carbol
 * @version 0.1
 */
public abstract class Map {
    protected List<Platform> platforms= new ArrayList<>();

    /**
     * The public constructor for the Map class.
     *
     */
    public Map() {}

    /**
     * Initiates the list of platforms depending on which map is loaded.
     */

     public abstract void initPlatforms();

    public List<Platform> getPlatforms() {
        return this.platforms;
    }
}
