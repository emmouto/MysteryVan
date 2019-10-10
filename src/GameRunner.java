import Controller.EnemyController;
import Controller.KeyController;
import Controller.MapController;
import Controller.PlayerController;
import View.*;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.environment.CreatureMapObjectLoader;
import de.gurkenlabs.litiengine.environment.PropMapObjectLoader;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.gui.screens.Resolution;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;
import java.io.IOException;

/**
 * Sets up all the screens and runs the game.
 *
 * @author Jonathan Carbol
 * @author Jennifer Krogh
 * @author Emma Pettersson
 * @author Adam Rohdell
 * @author Antonia Welzel
 * @version 0.1
 */
public class GameRunner {
    /**
     * ...
     *
     * @param args the command line arguments.
     * @throws java.io.IOException when...
     */
    public static void main(String[] args) throws IOException {
        MapController mapController = new MapController();

        Game.config().graphics().setResolutionHeight(720);
        Game.config().graphics().setResolutionWidth(1280);
        Game.config().graphics().setFullscreen(true);

        Game.setInfo("gameinfo.xml");

        Game.init(args);
        Input.mouse().setGrabMouse(false);
        Game.window().setResolution(Resolution.custom(1280, 720, "720p"));
        Game.window().setIconImage(Resources.images().get("src/main/resources/icon.png"));

        // Adds all the screens
        Game.screens().add(new MenuView("Menu"));
        Game.screens().add(new SelectionView("Selection"));
        Game.screens().add(new HelpView("Help"));
        Game.screens().add(new HighScoreView("HighScore"));
        Game.screens().add(new DefeatView("Defeat"));
        Game.screens().add(new PauseView("Pause"));
        Game.screens().add(new GameView("Game"));
        Game.screens().add(new GameScreen());

        // TODO move some of this code somewhere else..?
            /*Game.graphics().setBaseRenderScale(2.001f);
            Resources.load("game.litidata");*/

            PlayerController playerController = new PlayerController();
            /*EnemyController enemyController = new EnemyController(playerController.getPlayers());
            KeyController keyController = new KeyController(playerController);*/

            /*mapController.initCamera();
            enemyController.loadMap(mapController.getMap());*/
            playerController.loadMap(mapController.getMap());

            playerController.setGameView(Game.screens().get("Game"));
            Resources.spritesheets().get("Bread", true);
           /* CreatureMapObjectLoader.registerCustomCreatureType(enemyController.getCreatures().get(0).getClass());
            CreatureMapObjectLoader.registerCustomCreatureType(playerController.getCreatures().get(0).getClass());

            Game.loop().attach(enemyController);
            Game.loop().attach(playerController);

            Game.world().loadEnvironment("new_map");
            Game.world().environment().add(enemyController.getCreatures().get(0));
            playerController.getCreatures().get(0).setLocation(250,100);
            Game.world().environment().add(playerController.getCreatures().get(0));*/

        // Displays the title screen ("Menu").
        Game.screens().display("Menu");

        Game.start();
    }
}
