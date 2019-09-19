package Model;

import java.util.List;

public class Map {


    private Ground groundType;
    private List<Platform> platforms;
    private List<Item> items;
    private List<Enemy> enemies;


    public Ground getGroundType() {
        return groundType;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }
}
