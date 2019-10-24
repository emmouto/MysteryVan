package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {

    @Test
    void determineARandomFoodFromThreeOptions() {

        Food f = new Food(1,1);


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