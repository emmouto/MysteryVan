package Model.Maps;

import Model.Map;
import Model.Platform;

/**
 * A class for a medium map.
 *
 * @author Jonathan Carbol
 * @version 0.1
 */
public class MapMedium extends Map {
    /**
     * Constructor for a map with medium difficulty.
     */
    public MapMedium() {
        super();
        initPlatforms();
    }

    @Override
    public void initPlatforms() {
        platforms.add(new Platform(0,96,48,160));
        platforms.add(new Platform(480,96,48,170));
        platforms.add(new Platform(0,320,48,160));
        platforms.add(new Platform(480,320,48,170));
        platforms.add(new Platform(192,192,16,288));
        platforms.add(new Platform(160,451,1,480-160));
        //platforms.add(new Ground(160,451,1,480-160, Ground.Type.LAVA,true,false));
    }
}
