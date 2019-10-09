package Controller;

import Model.*;
import View.DefeatView;
import View.GameView;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.physics.MovementController;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO description
 *
 * @author
 */
public class PlayerController implements IUpdateable {
    List<Player> playerList = new ArrayList<>();
    private List<Creature> creatureList = new ArrayList<>();
    private Map map;
    private GameView gameView;

    private DefeatView dv;
    private HighScoreController hc;


    /**
     * TODO description
     */
    public PlayerController() {
        super();
        spawnPlayer("player", 23, 100, 0,new Hat("ugly", new Boost("boost", 0, 0, 0)), new Weapon("xd", 0, 0));
        updatePlayerController();
    }

    /**
     * TODO description
     */
    public void walkLeft() {
        while (true) {
            playerList.get(0).setPosX(playerList.get(0).getPosX() - 1);
        }
    }

    /**
     * TODO description
     */
    public void walkRight() {
        while (true) {
            playerList.get(0).setPosX(playerList.get(0).getPosX() + 1);
        }
    }

    /**
     * TODO description
     *
     * @param name
     *      TODO description
     * @param hp
     *      TODO description
     * @param defense
     *      TODO description
     * @param strength
     *      TODO description
     * @param hat
     *      TODO description
     * @param weapon
     *      TODO description
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
     * TODO description
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
     * TODO description
     */
    @Override
    public void update() {
        for (int i = 0; i < playerList.size(); i++) {
            getPlayers().get(i).update();
            getPlayers().get(i).checkGrounded(this.map.getPlatforms());
            creatureList.get(i).setLocation(playerList.get(i).getX(), playerList.get(i).getY());
            creatureList.get(i).setLocation(playerList.get(i).getX(),playerList.get(i).getY());
            updateHealth(i);
        }
    }

    /**
     * Checks if player is dead, if so, then this method handles what happens.
     */
    // Call on this method somewhere were the game is updated!
    private void whenDead(){

        HighScore newScore;

        for (Player p : playerList) {

    }

    public void updateHealth(int i){
        gameView.setHP(playerList.get(i).getHP());
        gameView.setMaxHP(playerList.get(i).getMaxHP());
        creatureList.get(i).getHitPoints().setMaxValue(playerList.get(i).getHP());
        creatureList.get(i).getHitPoints().setToMaxValue();
            if(p.getState() == Player.State.DEAD){
                dv.scoreDefeat(p.getScore());
                newScore = new HighScore(p.getScore(), p.getName());
                hc.addToScoreList(newScore);
                DefeatView.showDefeat();
            }
        }
    }

    /**
     * TODO description
     *
     * @param map
     *      TODO description
     */
    public void loadMap(Map map){
        this.map = map;
    }

    // Getters & Setters - JavaDoc not needed
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
}
