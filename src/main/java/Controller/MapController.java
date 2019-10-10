package Controller;

import Model.Map;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.Camera;

/**
 * ...
 *
 * @author
 * @version
 */
public class MapController {
    private Camera camera = new Camera();
    private Map map = new Map("map1");

    /**
     * Initiate the Camera to focus on the center of the map
     */
    public void initCamera(){
        camera.setFocus(Game.window().getResolution().getWidth() * 0.5 / camera.getRenderScale(), Game.window().getResolution().getHeight() * 0.5 / camera.getRenderScale());
        camera.setClampToMap(true);
        Game.world().setCamera(camera);
    }

    /**
     * ...
     *
     * @return the current map (?)
     */
    public Map getMap(){
        return this.map;
    }
}
