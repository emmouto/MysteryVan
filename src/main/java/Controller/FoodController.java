package Controller;

import Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * TODO description
 *
 * @author
 */
public class FoodController implements IUpdateable {
    private List<Food> food = new ArrayList<>();

    /**
     * TODO description
     */
    public void spawnFruit(){
        food.add(new Food());
    }

    /**
     * TODO description
     */
    @Override
    public void update() {

    }
}
