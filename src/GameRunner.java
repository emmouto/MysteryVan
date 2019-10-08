import Controller.EnemyController;
import Controller.MapController;
import Controller.PlayerController;
import View.*;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.environment.CreatureMapObjectLoader;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.gui.screens.Resolution;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.resources.Resources;

import java.io.IOException;

/**
 * Sets up all the screens and runs the game.
 *
 * @author Jonathan Carbol
 * @author Jennifer Krogh
 * @author Emma Pettersson
 * @author Adam Rohdell
 * @author Antonia Welzel
 */
public class GameRunner {
    /**
     * TODO description
     *
     * @param args
     *      The command line arguments.
     */
    public static void main(String[] args) throws IOException {
        MapController mc = new MapController();

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

        // Displays the title screen ("Menu").
        Game.screens().display("Game");

        /*
            Game.graphics().setBaseRenderScale(2.001f);
            Game.screens().add(new GameScreen());

            Resources.load("game.litidata");

            PlayerController pc = new PlayerController();
            EnemyController ec = new EnemyController(pc.getPlayers());
            mc.initCamera();
            ec.loadMap(mc.getMap());
            pc.loadMap(mc.getMap());

            CreatureMapObjectLoader.registerCustomCreatureType(ec.getCreatures().get(0).getClass());
            CreatureMapObjectLoader.registerCustomCreatureType(pc.getCreatures().get(0).getClass());

            Game.loop().attach(ec);
            Game.loop().attach(pc);

            Game.world().loadEnvironment("new_map");
            Game.world().environment().add(ec.getCreatures().get(0));
            pc.getCreatures().get(0).setLocation(0,100);
            Game.world().environment().add(pc.getCreatures().get(0));

         */

        Game.start();
    }
}
