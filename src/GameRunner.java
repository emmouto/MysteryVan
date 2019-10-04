import Controller.EnemyController;
import Controller.MapController;
import Controller.PlayerController;
import Model.Enemy;
import Model.Player;
import View.*;

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
import de.gurkenlabs.litiengine.input.PlatformingMovementController;
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

        Game.graphics().setBaseRenderScale(2.001f);
        Game.screens().add(new GameScreen());

        Resources.load("game.litidata");

        // Adds all the screens
        Game.screens().add(new MenuView("Menu"));
        Game.screens().add(new SelectionView("Selection"));
        Game.screens().add(new HelpView("Help"));
        Game.screens().add(new HighscoreView("Highscore"));
        Game.screens().add(new GameScreen());
        Game.screens().add(new DefeatView("Defeat"));
        Game.screens().add(new PauseView("Pause"));

        // Displays the title screen ("Menu").
        Game.screens().display("Main");

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
        Game.start();

    }
}
