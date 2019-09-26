import Controller.MapController;
import Model.Player2;
import View.InGameScreen;
import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.environment.Environment;
import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.gui.screens.Resolution;
import de.gurkenlabs.litiengine.resources.Resources;
import main.PlayerInput;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/** Runs the game.
 * @author xx, xxx, Emma Pettersson
 */
public class GameRunner {

    public static void main(String[] args) throws IOException {

        Game.config().graphics().setResolutionHeight(720);
        Game.config().graphics().setFullscreen(true);
        Game.config().graphics().setResolutionWidth(1280);

        Game.setInfo("gameinfo.xml");

        Game.init(args);
        MapController mc = new MapController();
        //PlayerController pc = new PlayerController(new Player("Player 1"));


        Game.window().setResolution(Resolution.custom(1280, 720, "720p"));

        /*GameScreen g = new GameScreen();
        g.setName("Game");
        Game.screens().add(g);*/

        Game.screens().add(new InGameScreen());
        PlayerInput.init();
        Game.screens().display("INGAME-SCREEN");
        BufferedImage bg;

        bg = ImageIO.read(new File("src/main/resources/golden_knight_animation_walk_right_edit_00005.png"));


        Spritesheet s = new Spritesheet(bg, "src/main/resources/golden_knight_animation_walk_right_edit_00005.png", 20, 20);

        Resources.spritesheets().add("knight", s);
        Environment environment = new Environment("src/main/resources/new_map.tmx");
        environment.add(Player2.instance());
        //Environment.registerMapObjectLoader(new CustomCreatureMapObjectLoader());
        //CustomCreatureMapObjectLoader.registerCustomCreatureType(Player2.instance().getClass());
        //Environment.registerCustomEntityType("src/main/resources/golden knight animation walking right edit_00005.png",Player2.instance().getClass());

        Game.audio().playMusic(Resources.sounds().get("src/main/resources/sounds/title_theme.mp3"));

        //pc.playerInit();
        mc.initCamera();


        // add default game logic for when a level was loaded
        Game.world().addLoadedListener(e -> {

            /*if (e.getMap().getName().equals("title")) {
                return;
            }*/

            Game.loop().perform(500, () -> Game.window().getRenderComponent().fadeIn(500));


            // spawn the player instance on the spawn point with the name "enter"
            Spawnpoint enter = new Spawnpoint(0, 0);
            enter.setDirection(Direction.DOWN);
            if (enter != null) {
                enter.spawn(Player2.instance());
            }
        });

        Game.world().loadEnvironment(environment);
        Game.start();
    }


}
