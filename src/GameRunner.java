import Controller.MapController;
import View.MenyView;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.GameListener;
import de.gurkenlabs.litiengine.environment.Environment;
import de.gurkenlabs.litiengine.environment.tilemap.*;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.gui.screens.Resolution;
import de.gurkenlabs.litiengine.resources.Resource;
import de.gurkenlabs.litiengine.resources.Resources;


import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import java.util.List;
import java.util.Map;


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

        Game.world().loadEnvironment(new Environment("src/main/resources/new_map.tmx"));


        mc.initCamera();






        Game.start();


    }
}
