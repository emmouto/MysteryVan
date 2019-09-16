import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.GameListener;

public class GameRunner {

    public static void main(String[] args) {

        Game.setInfo("gameinfo.xml");
        Game.init(args);
        Game.start();


    }
}
