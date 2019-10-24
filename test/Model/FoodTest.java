package Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {

    @Test
    void determineARandomFoodFromThreeOptions() {

        List<Platform> ps = new ArrayList<>();
        Player p = new Player("sprite", 1, 1, 1, 1, ps);
        Food f = new Food(1,1, p);


        while(!f.getName().equals("beer")) {
            f.determineFood();
        }

        assertEquals(f.getName(), "beer");

        while(!f.getName().equals("bread")) {
            f.determineFood();
        }

        assertEquals(f.getName(), "bread");

        while(!f.getName().equals("rottenfruit")) {
            f.determineFood();
        }

        assertEquals(f.getName(), "rottenfruit");



    }
}