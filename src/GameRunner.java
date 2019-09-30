import Controller.EnemyController;
import Controller.MapController;
import Controller.PlayerController;
import Model.Enemy;
import Model.Player;
import com.sun.javafx.iio.png.PNGImageLoader2;
import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.RenderLoop;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityControllers;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.environment.CreatureMapObjectLoader;
import de.gurkenlabs.litiengine.environment.CustomMapObjectLoader;
import de.gurkenlabs.litiengine.environment.Environment;
import de.gurkenlabs.litiengine.environment.MapObjectLoader;
import de.gurkenlabs.litiengine.graphics.RenderComponent;
import de.gurkenlabs.litiengine.graphics.RenderEngine;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.gui.screens.Resolution;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.resources.Resource;
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

        Resources.load("game.litidata");

        Game.audio().playMusic(Resources.sounds().get("src/main/resources/sounds/title_theme.mp3"));

        PlayerController pc = new PlayerController(new Player("player"));
        EnemyController ec = new EnemyController(pc.getCreatures());
        mc.initCamera();

        CreatureMapObjectLoader.registerCustomCreatureType(ec.getCreatures().get(0).getClass());
        CreatureMapObjectLoader.registerCustomCreatureType(pc.getCreatures().get(0).getClass());

        Game.loop().attach(ec);
        Game.loop().attach(pc);

        Game.world().loadEnvironment("new_map");
        Game.world().environment().add(ec.getCreatures().get(0));
        pc.getCreatures().get(0).setLocation(0,100);//Game.screens().current().getWidth()/4,Game.screens().current().getHeight()/4);
        Game.world().environment().add(pc.getCreatures().get(0));
        Game.start();



    }
}
