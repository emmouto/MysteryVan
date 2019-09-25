import Controller.MapController;
import Controller.PlayerController;
import Model.Player;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.environment.CreatureMapObjectLoader;
import de.gurkenlabs.litiengine.environment.Environment;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.gui.screens.Resolution;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.event.KeyEvent;


/** Runs the game.
 * @author xx, xxx, Emma Pettersson
 */
public class GameRunner {

    public static void main(String[] args) {

        Game.config().graphics().setResolutionHeight(720);
        Game.config().graphics().setFullscreen(true);
        Game.config().graphics().setResolutionWidth(1280);

        Game.setInfo("gameinfo.xml");

        Game.init(args);
        MapController mc = new MapController();
        PlayerController pc = new PlayerController(new Player("Player 1"));


        Game.window().setResolution(Resolution.custom(1280, 720, "720p"));

        GameScreen g = new GameScreen();
        g.setName("Game");
        Game.screens().add(g);

        Game.world().loadEnvironment(new Environment("src/main/resources/new_map.tmx"));

        Game.audio().playMusic(Resources.sounds().get("src/main/resources/sounds/title_theme.mp3"));

        pc.playerInit();
        mc.initCamera();


        // add default game logic for when a level was loaded
        Game.world().addLoadedListener(e -> {

            if (e.getMap().getName().equals("title")) {
                return;
            }

            Game.loop().perform(500, () -> Game.window().getRenderComponent().fadeIn(500));


            // spawn the player instance on the spawn point with the name "enter"
            Spawnpoint enter = e.getSpawnpoint("enter");
            if (enter != null) {
                enter.spawn(Player.instance());
            }
        });


        Game.start();
    }


}
