package Controller;

import Model.Hat;
import Model.Player;
import Model.Weapon;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.physics.MovementController;

import java.util.ArrayList;
import java.util.List;

public class PlayerController implements IUpdateable {

    List<Player> playerList = new ArrayList<>();
    List<Creature> creatureList = new ArrayList<>();


    public PlayerController() {
        super();
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
        Player p = new Player(name);
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
            creatureList.get(i).setLocation(playerList.get(i).getX(),playerList.get(i).getY());
        }

    }
}
