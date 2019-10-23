package Controller;

import Model.Enemy;
import Model.GameLoop;
import View.*;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.environment.CreatureMapObjectLoader;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.gui.screens.Resolution;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.resources.Resources;
/**
 * Initializes and controls the game.
 *
 * @author Jonathan Carbol
 * @author Jennifer Krogh
 * @author Emma Pettersson
 * @author Adam Rohdell
 * @author Antonia Welzel
 * @version 0.1
 */
public class GameController {
    private GameLoop gameLoop;
    private PlayerController playerController;
    private EnemyController enemyController;

    /**
     *
      */
    public GameController() {
        GameLoop.getInstance();
    }

    /**
     * Initializes the game.
     */
    public void init() {
        MapController mapController = new MapController();

        Game.config().graphics().setResolutionHeight(720);
        Game.config().graphics().setResolutionWidth(1280);
        Game.config().graphics().setFullscreen(true);

        Game.setInfo("gameinfo.xml");

        Game.init();
        Input.mouse().setGrabMouse(false);
        Game.window().setResolution(Resolution.custom(1280, 720, "720p"));
        Game.window().setIconImage(Resources.images().get("src/main/resources/icon.png"));

        addScreens();

        Resources.load("game.litidata");
        playerController = new PlayerController(mapController.getMap());
        enemyController = new EnemyController(playerController.getPlayers(), mapController.getMap());
        KeyController keyController = new KeyController(playerController);

        mapController.initCamera();
        playerController.setGameView(Game.screens().get("Game"));

        CreatureMapObjectLoader.registerCustomCreatureType(enemyController.getCreatures().get(0).getClass());
        CreatureMapObjectLoader.registerCustomCreatureType(playerController.getCreatures().get(0).getClass());

        Game.loop().attach(enemyController);
        Game.loop().attach(playerController);

        Game.world().loadEnvironment("new_map");
        Game.world().environment().add(enemyController.getCreatures().get(0));
        playerController.getCreatures().get(0).setLocation(250,100);
        Game.world().environment().add(playerController.getCreatures().get(0));

        // Displays the title screen ("Menu")
        Game.screens().display("Menu");

        //Game.graphics().setBaseRenderScale(2.001f);
        Resources.spritesheets().get("AppleWorm", true);

        Game.start();
        addUpdateablesToGameLoop();
        GameLoop.getInstance().run();
    }

    private void addUpdateablesToGameLoop(){
        GameLoop.getInstance().addUpdateables(playerController.getPlayers().get(0));
        for (Enemy e : enemyController.getEnemies()){
            GameLoop.getInstance().addUpdateables(e);
        }
    }

    private void addScreens() {
        // Adds all the screens
        Game.screens().add(new MenuView("Menu"));
        Game.screens().add(new SelectionView("Selection"));
        Game.screens().add(new HelpView("Help"));
        Game.screens().add(new HighScoreView("HighScore"));
        Game.screens().add(new DefeatView("Defeat"));
        Game.screens().add(new PauseView("Pause"));
        Game.screens().add(new GameView("Game"));
        Game.screens().add(new GameScreen());
    }
}
