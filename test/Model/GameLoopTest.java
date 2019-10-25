package Model;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class GameLoopTest {

    /**
     * The Test Class for GameLoop
     * Unable to run these tests as the code never gets past 'g.run()' as it is an endless while Loop
     * If we could, this is what it would look like.
     */


    /*@Test
    void shouldStartTheGameLoop(){
        Player p = new Player("test",0,0,1,1, new ArrayList<Platform>());
        GameLoop g = GameLoop.getInstance();
        g.addUpdateables(p);
        g.run();
        assertTrue(p.getY() >= 3);
    }

    @Test
    void shouldTestIfDelayCanBeSetAndDone(){
        GameLoop g = GameLoop.getInstance();
        boolean test = false;
        int testTimer= 10000;
        g.setDelayTimer(10);
        while (!g.checkIfDelayDone()){
            testTimer--;
            if (testTimer == 0){
                test = false;
                break;
            }
            test = true;
        }
        assertTrue(test);
    }*/

}
