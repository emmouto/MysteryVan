package Model;

import java.util.List;

public class Map {


    private Ground groundType;
    private List<Plattform> platforms;
    private List<Item> items;
    private List<Enemies> enemies;


    public Ground getGroundType() {
        return groundType;
    }

    public List<Enemies> getEnemies() {
        return enemies;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Plattform> getPlatforms() {
        return platforms;
    }
}
