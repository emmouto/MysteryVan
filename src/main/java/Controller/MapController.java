package Controller;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.Camera;

public class MapController {

    Camera camera = new Camera();


    // Initiate the Camera to focus on the center of the map
    public void initCamera(){
        camera.setFocus(Game.window().getResolution().getWidth() * 0.5 / camera.getRenderScale(), Game.window().getResolution().getHeight() * 0.5 / camera.getRenderScale());
        camera.setClampToMap(true);
        Game.world().setCamera(camera);
    }
}
