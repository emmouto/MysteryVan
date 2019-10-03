package Model;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private String name;
    private List<Platform> platforms= new ArrayList<>();

    public Map(String name) {
        this.name = name;
        init(name);
    }

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
