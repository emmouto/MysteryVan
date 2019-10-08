package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO description
 *
 * @author
 */
public class Map {
    private String name;
    private List<Platform> platforms= new ArrayList<>();
    private Ground ground;

    /**
     * Constructor for the game map.
     *
     * @param name
     *      Name of the map,
     * @param ground
     *      Type of ground for the map.
     */
    public Map(String name, Ground ground) {
        this.name = name;
        this.ground = ground;
        init(name);
    }

    /**
     * TODO description
     *
     * @param name
     *      Name of the map.
     */
    public void init(String name){
        switch (name){
            case "map1" :   platforms.add(new Platform(0,112,48,160));
                            platforms.add(new Platform(480,112,48,160));
                            platforms.add(new Platform(120,336,48,160));
                            platforms.add(new Platform(480,336,48,160));
                            platforms.add(new Platform(192,208,16,288));
        }
    }

    public List<Platform> getPlatforms(){
        return this.platforms;
    }

}
