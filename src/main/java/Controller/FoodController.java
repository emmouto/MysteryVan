package Controller;

import Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ...
 *
 * @author Antonia Welzel
 * @author Jonathan Carbol
 * @version 0.1
 */
public class FoodController implements IUpdateable {
    private List<Food> food = new ArrayList<>();

    /**
     * ...
     */
    public void spawnFruit() {
        food.add(new Food());
    }

    /**
     * ...
     */
    @Override
    public void update() {

    }
}
