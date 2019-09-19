import Controller.MapController;
import View.MenuView;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.environment.Environment;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.gui.screens.Resolution;
import de.gurkenlabs.litiengine.resources.Resources;


/** Runs the game.
 * @author xx, xxx, Emma Pettersson
 */
public class GameRunner {

    public static void main(String[] args) {
        MapController mc = new MapController();

        Game.config().graphics().setResolutionHeight(720);
        Game.config().graphics().setFullscreen(true);
        Game.config().graphics().setResolutionWidth(1280);

        Game.setInfo("gameinfo.xml");

        Game.init(args);
        Game.window().setResolution(Resolution.custom(1280, 720, "720p"));

        Game.screens().add(new GameScreen());
        Game.screens().add(new MenuView("Menu"));
        Game.screens().display("Menu");

        Game.world().loadEnvironment(new Environment("src/main/resources/new_map.tmx"));

        Game.audio().playMusic(Resources.sounds().get("src/main/resources/sounds/music/title_theme.mp3"));

        mc.initCamera();

        Game.start();
    }
}
