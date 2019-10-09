package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan Carbol
 * The Map class for the project. It containes a list of platforms that are used to check player/enemy movements such as gravity.
 */
public class Map {

    private String name;
    private List<Platform> platforms= new ArrayList<>();

    /**
     * The public contructor for the Map class.
     * @param name the name of the map.
     */
    public Map(String name) {
        this.name = name;
        init(name);
    }

    /**
     * Initiates the list of platforms depending on which map is loaded.
     * @param name
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
