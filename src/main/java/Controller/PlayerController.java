package Controller;

import Model.Player;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.physics.MovementController;

import java.util.ArrayList;
import java.util.List;

public class PlayerController {

    List<Player> playerList = new ArrayList<>();
    List<Creature> creatureList = new ArrayList<>();


    public PlayerController(Player p1) {
        super();
        playerList.add(p1);
        for (int i = 0; i <= playerList.size(); i++){
            Creature c = new Creature();
            creatureList.add(c);
            creatureList.get(i).getHitPoints().setMaxValue(10);
            creatureList.get(i).getHitPoints().setToMaxValue();
            creatureList.get(i).getHitBox().getBounds().height=p1.getHeight();
            creatureList.get(i).getHitBox().getBounds().width=p1.getWidth();
            creatureList.get(i).setAcceleration(50);
            creatureList.get(i).setSpritePrefix(p1.getSprite());

        }
    }

    public void walkLeft(){
        while(true){
            playerList.get(0).setPosX(playerList.get(0).getPosX()-1);
        }
    }

    public void walkRight(){
        while(true){
            playerList.get(0).setPosX(playerList.get(0).getPosX()+1);
        }
    }

    public List<Player> getPlayers() {
        return playerList;
    }

    public List<Creature> getCreatures() {
        return creatureList;
    }
}
