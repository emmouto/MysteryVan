package Model.Maps;

import Model.Map;
import Model.Platform;

import java.util.ArrayList;
import java.util.List;

public class MapEasy extends Map {

    private String name;

    /**
     * The public constructor for the Map class.
     *
     * @param name the name of the map.
     */
    public MapEasy(String name) {
        super(name);
        initPlatforms();
    }

    @Override
    public void initPlatforms() {
        platforms.add(new Platform(0,96,48,160));
        platforms.add(new Platform(480,96,48,170));
        platforms.add(new Platform(192,192,16,288));
        platforms.add(new Platform(0,320,16,660));
    }
}
