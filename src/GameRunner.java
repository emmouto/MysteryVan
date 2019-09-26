import Controller.EnemyController;
import Controller.MapController;
import Model.Enemy;
import Model.Player;
import com.sun.javafx.iio.png.PNGImageLoader2;
import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.RenderLoop;
import de.gurkenlabs.litiengine.entities.EntityControllers;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.environment.CreatureMapObjectLoader;
import de.gurkenlabs.litiengine.environment.Environment;
import de.gurkenlabs.litiengine.environment.MapObjectLoader;
import de.gurkenlabs.litiengine.graphics.RenderComponent;
import de.gurkenlabs.litiengine.graphics.RenderEngine;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.gui.screens.Resolution;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.resources.Resources;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;


/** Runs the game.
 * @author xx, xxx, Emma Pettersson
 */
public class GameRunner {

    public static void main(String[] args) throws IOException {
        MapController mc = new MapController();


        Game.config().graphics().setResolutionHeight(720);
        Game.config().graphics().setFullscreen(true);
        Game.config().graphics().setResolutionWidth(1280);

        Game.setInfo("gameinfo.xml");

        Game.init(args);
        Input.mouse().setGrabMouse(false);
        Game.window().setResolution(Resolution.custom(1280, 720, "720p"));

        Game.screens().add(new GameScreen());



        Game.audio().playMusic(Resources.sounds().get("src/main/resources/sounds/title_theme.mp3"));


        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("C:\\Users\\adam_\\OneDrive\\Dokument\\TDA367\\MysteryVan\\src\\main\\resources\\textures\\Enemy\\enemy-walk-right.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        BufferedImage img2 = null;
        try {
            img2 = ImageIO.read(new File("C:\\Users\\adam_\\OneDrive\\Dokument\\TDA367\\MysteryVan\\src\\main\\resources\\textures\\Enemy\\enemy-idle-left.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Resources.images().add("enemy-walk-left", img);
        Resources.images().add("enemy_idle", img2);
        EnemyController ec = new EnemyController(new ArrayList<Player>());
        mc.initCamera();

        CreatureMapObjectLoader.registerCustomCreatureType(Enemy.class);



        Game.world().loadEnvironment(new Environment("src/main/resources/new_map.tmx"));
        Game.start();

    }
}
