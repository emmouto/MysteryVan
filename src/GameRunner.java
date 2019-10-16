import Controller.*;
import Model.Food;
import View.*;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.IEntity;
import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.environment.CreatureMapObjectLoader;
import de.gurkenlabs.litiengine.environment.PropMapObjectLoader;
import de.gurkenlabs.litiengine.environment.tilemap.xml.MapObject;
import de.gurkenlabs.litiengine.graphics.IRenderable;
import de.gurkenlabs.litiengine.graphics.RenderType;
import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.graphics.emitters.SpritesheetEmitter;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.gui.screens.Resolution;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.geom.Point2D;
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

        Resources.load("game.litidata");
        PlayerController playerController = new PlayerController();
        EnemyController enemyController = new EnemyController(playerController.getPlayers());
        KeyController keyController = new KeyController(playerController);
        FoodController fc = new FoodController(playerController.getPlayers());

        mapController.initCamera();
        fc.loadMap(mapController.getMap());
        enemyController.loadMap(mapController.getMap());
        playerController.loadMap(mapController.getMap());
        playerController.setGameView(Game.screens().get("Game"));

        CreatureMapObjectLoader.registerCustomCreatureType(enemyController.getCreatures().get(0).getClass());
        CreatureMapObjectLoader.registerCustomCreatureType(playerController.getCreatures().get(0).getClass());

        Game.loop().attach(enemyController);
        Game.loop().attach(playerController);
        //Game.loop().attach(fc);

        Game.world().loadEnvironment("new_map");
        Game.world().environment().add(enemyController.getCreatures().get(0));
        playerController.getCreatures().get(0).setLocation(250,100);
        Game.world().environment().add(playerController.getCreatures().get(0));


        PropMapObjectLoader.registerCustomPropType(fc.getPropList().get(0).getClass());
        Game.world().environment().add(fc.getPropList().get(0));




        Game.screens().display("Menu");





        // TODO move some of this code somewhere else..?
            /*Game.graphics().setBaseRenderScale(2.001f);*/



           /*



            */

        // Displays the title screen ("Menu").


        Game.start();
    }
}
