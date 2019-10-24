package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Map class for the project. It containes a list of platforms that are used to check player/enemy movements such as gravity.
 *
 * @author Jonathan Carbol
 * @version 0.1
 */
public class Map {
    private String name;
    private List<Platform> platforms= new ArrayList<>();

    /**
     * The public constructor for the Map class.
     *
     * @param name the name of the map.
     */
    public Map(String name) {
        this.name = name;
        init(name);
    }

    /**
     * Initiates the list of platforms depending on which map is loaded.
     *
     * @param name The name of the map.
     */
    private void init(String name) {
        switch (name) {
            case "map1" :   platforms.add(new Platform(0,96,48,160));
                            platforms.add(new Platform(480,96,48,170));
                            platforms.add(new Platform(0,320,48,160));
                            platforms.add(new Platform(480,320,48,170));
                            platforms.add(new Platform(192,192,16,288));
                            break;
                            //platforms.add(new Ground(160,451,1,480-160, Ground.Type.SKY,false,true));
            case "map2" :   platforms.add(new Platform(0,96,48,160));
                            platforms.add(new Platform(480,96,48,170));
                            platforms.add(new Platform(192,192,16,288));
                            platforms.add(new Platform(0,320,16,660));
                            break;
            case "map3" :   platforms.add(new Platform(0,96,48,160));
                            platforms.add(new Platform(480,96,48,170));
                            platforms.add(new Platform(0,320,48,160));
                            platforms.add(new Platform(480,320,48,170));
                            platforms.add(new Platform(192,192,16,288));
                            platforms.add(new Platform(160,451,1,480-160));
                            break;
                            //platforms.add(new Ground(160,451,1,480-160, Ground.Type.LAVA,true,false));
        }
    }

    public List<Platform> getPlatforms() {
        return this.platforms;
    }
}
