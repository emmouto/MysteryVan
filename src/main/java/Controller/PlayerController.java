package Controller;

import Model.*;
import View.DefeatView;
import View.GameManager;
import View.GameView;

import View.HighScoreView;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.gui.screens.Screen;

import java.util.ArrayList;
import java.util.List;

/**
 * The PlayerController class.
 * It connects the Players from the model to a external game engine in order to use its functions.
 * It implements an IUpdatable interface which makes sure that it is updated in the game loop.
 *
 * @author Jonathan Carbol
 * @author Jennifer Krogh
 * @version 0.1
 */
public class PlayerController implements IUpdateable {
    static List<Player> playerList = new ArrayList<>();
    private List<Creature> creatureList = new ArrayList<>();
    private Map map;
    private GameView gameView;

    private ScreenController screenController = new ScreenController(0, 0, 0, 0, "");
    private KeyController keyController;

    /**
     * The public constructor of the PlayerController.
     */
    public PlayerController(Map map) {
        super();
        this.map=map;
        spawnPlayer("ADAM", 23, 100, 0, new Hat("ugly", new Boost(0, 0, 0)), new Weapon("xd", 5, 10));
        updatePlayerController();
        screenController = new ScreenController(0, 0, 0, 0, "");
    }

    /**
     * ...
     */
    public void walkLeft() {
        while (true) {
            playerList.get(0).setPosX(playerList.get(0).getPosX() - 1);
        }
    }

    /**
     * ...
     */
    public void walkRight() {
        while (true) {
            playerList.get(0).setPosX(playerList.get(0).getPosX() + 1);
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
     *
     * @param name the name of the player.
     * @param hp the hp of the player.
     * @param defense the defense of the player.
     * @param strength the strength of the player.
     * @param hat the equipped hat of the player.
     * @param weapon the equipped weapon of the player.
     */
    private void spawnPlayer(String name, int hp, int defense, int strength, Hat hat, Weapon weapon){
        Player p = new Player(name, 100, 100, 18, 35, this.map.getPlatforms());
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
    private void updatePlayerController(){
        if (!playerList.isEmpty()) {
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
        if (GameManager.getState() == GameManager.GameState.INGAME) {
            for (int i = 0; i < playerList.size(); i++) {
                if(playerList.get(i).getSprite()!=creatureList.get(i).getSpritePrefix()){
                    creatureList.get(i).setSpritePrefix(playerList.get(i).getSprite());
                }
                playerList.get(i).setState(Player.State.ALIVE);
                creatureList.get(i).setLocation(playerList.get(i).getX(), playerList.get(i).getY());
                updateHealth(i);
                updateScore(i);
                whenDead(i);
                screenController.changeToPause();
            }
        }
        else if(GameManager.getState() == GameManager.GameState.INGAME_PAUSE){
            screenController.changeToPause();
        }
    }

    /**
     * Checks if player is dead, if so, then this method handles what happens.
     */
    private void whenDead(int i) {
            if (playerList.get(i).getState() == Player.State.DEAD) {
                DefeatView defeatView;
                HighScoreView highScoreView;

                HighScore newScore;
                defeatView = (DefeatView) Game.screens().get("Defeat");
                defeatView.scoreDefeat(playerList.get(i).getScore());

                newScore = new HighScore(playerList.get(i).getScore(), playerList.get(i).getName());
                highScoreView = (HighScoreView) Game.screens().get("HighScore");
                highScoreView.hc.addToScoreList(newScore);

                screenController.changeScreen("Defeat", 500);
                GameManager.setState(GameManager.GameState.DEFEAT_SCREEN);
            }
    }

    /**
     * Updates the health of the players and sends the data to the view to be displayed.
     * @param i the index of the player to be updated.
     */
    private void updateHealth(int i) {
        gameView.setHP(playerList.get(i).getHP());
        gameView.setMaxHP(playerList.get(i).getMaxHP());
        //creatureList.get(i).getHitPoints().setMaxValue(playerList.get(i).getHP());
        //creatureList.get(i).getHitPoints().setToMaxValue();

       if (playerList.get(i).getHP() <= 0) {
           playerList.get(i).setState(Player.State.DEAD);
       }
    }

    /**
     * Updates the score of the player and sends the data to the view to be displayed.
     *
     * @param i the index of the player to be updated.
     */
    private void updateScore(int i) {
        gameView.setScore(playerList.get(i).getScore());
    }
}
