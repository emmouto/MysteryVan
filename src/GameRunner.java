import Controller.MapController;
import View.*;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.environment.Environment;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.gui.screens.Resolution;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.resources.Resources;


/**
 * Sets up all the screens and runs the game.
 *
 * @author Mystery Inc.
 */
public class GameRunner {
    /**
     * @param args
     *      The command line arguments.
     */
    public static void main(String[] args) {
        //MapController mc = new MapController();

        Game.config().graphics().setResolutionHeight(720);
        Game.config().graphics().setResolutionWidth(1280);
        Game.config().graphics().setFullscreen(true);

        Game.setInfo("gameinfo.xml");

        Game.init(args);
        Game.window().setResolution(Resolution.custom(1280, 720, "720p"));
        Game.window().setIconImage(Resources.images().get("src/main/resources/icon.png"));

        // Adds all the screens, and displays the title screen ("Menu").
        Game.screens().add(new MenuView("Menu"));
        Game.screens().add(new SelectionView("Selection"));
        Game.screens().add(new HelpView("Help"));
        Game.screens().add(new HighscoreView("Highscore"));
        Game.screens().add(new GameScreen());
        Game.screens().add(new DefeatView("Defeat"));
        Game.screens().add(new PauseView("Pause"));
        Game.screens().display("Menu");

        // TODO move this to GameView..?
        //Game.world().loadEnvironment(new Environment("src/main/resources/new_map.tmx"));
        //mc.initCamera();
        Game.start();
    }
}
