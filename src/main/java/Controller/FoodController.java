package Controller;

import Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ...
 *
 * @author
 * @version
 */
public class FoodController implements IUpdateable {
    private List<Food> food = new ArrayList<>();

    /**
     * ...
     */
    public void spawnFruit(){
        food.add(new Food());
    }

    /**
     * ...
     */
    @Override
    public void update() {

    }
}
