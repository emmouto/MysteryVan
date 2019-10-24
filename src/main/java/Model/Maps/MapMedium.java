package Model.Maps;

import Model.Map;
import Model.Platform;

import java.util.ArrayList;
import java.util.List;

public class MapMedium extends Map {

    private String name;

    /**
     * The public constructor for the Map class.
     *
     * @param name the name of the map.
     */
    public MapMedium(String name) {
        super(name);
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
