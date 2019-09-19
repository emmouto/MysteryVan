package Controller;

import Model.Player;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.IMobileEntity;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.input.PlatformingMovementController;

public class PlayerController extends PlatformingMovementController {

    Player p1 = new Player("player1");


    public PlayerController(IMobileEntity entity) {
        super(entity);
    }

    public PlayerController(IMobileEntity entity, int jump) {
        super(entity, jump);
    }

    public Player getPlayer1() {
        return p1;
    }
}
