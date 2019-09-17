import View.MenyView;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.GameListener;
import de.gurkenlabs.litiengine.environment.Environment;
import de.gurkenlabs.litiengine.environment.tilemap.*;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.resources.Resource;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class GameRunner {

    public static void main(String[] args) {

        Game.setInfo("gameinfo.xml");
        Game.init(args);


        Game.screens().add(new GameScreen());

        Game.world().loadEnvironment(new Environment("src/main/resources/map2.tmx"));

        Game.start();


    }
}
