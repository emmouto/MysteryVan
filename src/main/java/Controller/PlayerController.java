package Controller;

import Model.*;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.physics.MovementController;

import java.util.ArrayList;
import java.util.List;

public class PlayerController implements IUpdateable {

    List<Player> playerList = new ArrayList<>();
    List<Creature> creatureList = new ArrayList<>();
    private Map map;


    public PlayerController() {
        super();
        spawnPlayer("player", 100, 100, 0,new Hat("ugly", new Boost("boost", 0, 0, 0)), new Weapon("xd", 0, 0));
        updatePlayerController();
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

    public Creature getPlayer1(){
        return creatureList.get(0);
    }

    public void spawnPlayer(String name, int hp, int defense, int strength, Hat hat, Weapon weapon){
<<<<<<< HEAD
        Player p = new Player(name,0,0,16,16);
=======
        Player p = new Player(name, 0, 0, 18, 35);
>>>>>>> 532e70b2ce524c2081f80755eee6543b97388a8f
        p.setHP(hp);
        p.setDefence(defense);
        p.setHat(hat);
        p.setStrength(strength);
        p.setWeapon(weapon);
        playerList.add(p);
    }

    public void updatePlayerController(){
        if(!playerList.isEmpty()) {
            for (int i = 0; i < playerList.size(); i++) {
                Creature c = new Creature();
                creatureList.add(c);
                creatureList.get(i).getHitPoints().setMaxValue(10);
                creatureList.get(i).getHitPoints().setToMaxValue();
                creatureList.get(i).getHitBox().getBounds().height = playerList.get(i).getHeight();
                creatureList.get(i).getHitBox().getBounds().width = playerList.get(i).getWidth();
                creatureList.get(i).setAcceleration(50);
                creatureList.get(i).setSpritePrefix(playerList.get(i).getSprite());
            }
        }
    }

    @Override
    public void update() {
        for(int i = 0; i < playerList.size(); i++){
            getPlayers().get(i).update();
            getPlayers().get(i).checkGrounded(this.map.getPlatforms());
            creatureList.get(i).setLocation(playerList.get(i).getX(),playerList.get(i).getY());
        }

    }

    public void loadMap(Map map){
        this.map = map;
    }
}
