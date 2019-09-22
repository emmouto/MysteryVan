import Controller.MapController;
import View.HighscoreView;
import View.MenuView;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.environment.Environment;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.gui.screens.Resolution;
import de.gurkenlabs.litiengine.resources.Resources;


/** Runs the game.
 *
 * @param args  The command line arguments.
 */
public class GameRunner {

    public static void main(String[] args) {
        MapController mc = new MapController();

        Game.config().graphics().setResolutionHeight(720);
        Game.config().graphics().setResolutionWidth(1280);
        Game.config().graphics().setFullscreen(true);

        Game.setInfo("gameinfo.xml");

        Game.init(args);
        Game.window().setResolution(Resolution.custom(1280, 720, "720p"));
        //Game.window().setIconImage(Resources.images().get("src/main/resources/...")); // TODO fix icon

        Game.screens().add(new GameScreen());
        Game.screens().add(new HighscoreView("Highscore"));
        Game.screens().add(new MenuView("Menu"));
        Game.screens().display("Menu");

        Game.audio().playMusic(Resources.sounds().get("src/main/resources/audio/music/title_theme.mp3"));

        // TODO move this to GameView..?
        Game.world().loadEnvironment(new Environment("src/main/resources/new_map.tmx"));
        mc.initCamera();
        Game.start();
    }
}
