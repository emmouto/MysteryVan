package Controller;

import Model.*;
import View.GameView;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.physics.MovementController;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Jonathan Carbol
 * The PlayerController class. It connects the Players from the model to a external game engine in order to use its functions.
 * It implements an IUpdatable interface which makes sure that it is updated in the game loop.
 */
public class PlayerController implements IUpdateable {

    List<Player> playerList = new ArrayList<>();
    List<Creature> creatureList = new ArrayList<>();
    private Map map;
    private GameView gameView;


    /**
     * The public constructor of the PlayerController.
     */
    public PlayerController() {
        super();
        spawnPlayer("player", 23, 100, 0,new Hat("ugly", new Boost("boost", 0, 0, 0)), new Weapon("xd", 0, 0));
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

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(Screen gameView) {
        this.gameView = (GameView) gameView;
    }

    /**
     * Spawns a player with certain attributes.
     * @param name the name of the player.
     * @param hp the hp of the player.
     * @param defense the defense of the player.
     * @param strength the strength of the player.
     * @param hat the equipped hat of the player.
     * @param weapon the equipped weapon of the player.
     */
    public void spawnPlayer(String name, int hp, int defense, int strength, Hat hat, Weapon weapon){
        Player p = new Player(name, 0, 0, 18, 35);
        p.setHP(hp);
        p.setDefence(defense);
        p.setHat(hat);
        p.setStrength(strength);
        p.setWeapon(weapon);
        playerList.add(p);
    }

    /**
     * Updates the PlayerController, by setting the displayed creatures HP, hitbox and sprite among other things.
     */
    public void updatePlayerController(){
        if(!playerList.isEmpty()) {
            for (int i = 0; i < playerList.size(); i++) {
                Creature c = new Creature();
                creatureList.add(c);
                creatureList.get(i).getHitPoints().setMaxValue(playerList.get(i).getMaxHP());
                creatureList.get(i).getHitPoints().setToMaxValue();
                creatureList.get(i).getHitBox().getBounds().height = playerList.get(i).getHeight();
                creatureList.get(i).getHitBox().getBounds().width = playerList.get(i).getWidth();
                creatureList.get(i).setAcceleration(50);
                creatureList.get(i).setSpritePrefix(playerList.get(i).getSprite());
            }
        }

    }

    /**
     * Updates the players in the PlayerController, for their position, their gravity application and health.
     */
    @Override
    public void update() {
        for(int i = 0; i < playerList.size(); i++){
            getPlayers().get(i).update();
            getPlayers().get(i).checkGrounded(this.map.getPlatforms());
            creatureList.get(i).setLocation(playerList.get(i).getX(),playerList.get(i).getY());
            updateHealth(i);
        }

    }

    /**
     * Updates the health of the players and sends the data to the view to be displayed.
     * @param i the index of the player to be updated.
     */
    public void updateHealth(int i){
        gameView.setHP(playerList.get(i).getHP());
        gameView.setMaxHP(playerList.get(i).getMaxHP());
        creatureList.get(i).getHitPoints().setMaxValue(playerList.get(i).getHP());
        creatureList.get(i).getHitPoints().setToMaxValue();
    }

    /**
     * Loads the map of the into the PlayerController.
     * @param map the map to be loaded.
     */
    public void loadMap(Map map){
        this.map = map;
    }
}
