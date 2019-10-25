package Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {

    @Test
    void determineARandomFoodFromThreeOptions() {
        List<Platform> platformList = new ArrayList<>();
        Player player = new Player("sprite", 1, 1, 1, 1, platformList);
        Food food = new Food(1,1, player);

        while (!food.getName().equals("beer")) {
            food.determineFood();
        }

        assertEquals(food.getName(), "beer");

        while (!food.getName().equals("bread")) {
            food.determineFood();
        }

        assertEquals(food.getName(), "bread");

        while (!food.getName().equals("rottenfruit")) {
            food.determineFood();
        }

        assertEquals(food.getName(), "rottenfruit");
    }
}