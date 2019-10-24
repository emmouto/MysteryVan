package Controller;

import Model.Map;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.Camera;

/**
 * Controller for the game map.
 *
 * @author Adam Rohdell
 * @author Jonathan Carbol
 * @version 0.1
 */
public class MapController {
    private Camera camera = new Camera();
    private Map map = new Map("map1");

    public Map getMap() {
        return this.map;
    }

    /**
     * Initiate the Camera to focus on the center of the map
     */
    void initCamera() {
        camera.setFocus(Game.window().getResolution().getWidth() * 0.5 / camera.getRenderScale(),
                Game.window().getResolution().getHeight() * 0.5 / camera.getRenderScale());
        camera.setClampToMap(true);
        Game.world().setCamera(camera);
    }

    public String getMapName() {
        return this.map.getName();
    }
}
