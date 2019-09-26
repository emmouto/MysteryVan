package View;

import Model.Player;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.environment.tilemap.MapProperty;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.util.MathUtilities;
import sun.security.util.Resources_de;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GameView extends GameScreen implements IUpdateable {


    private static final int CINEMATIC_BORDER = 100;
    public static long levelNameTick;
    private static final int LEVELNAME_DURATION = 7000;


    protected GameView(String screenName) {
        super(screenName);
    }

    @Override
    public void prepare() {
        super.prepare();
        Game.loop().attach(this);

    }

    @Override
    public void suspend() {
        super.suspend();
        Game.loop().detach(this);
        Game.audio().stopMusic();
    }

    @Override
    public void render(Graphics2D g) {
        if (Player.instance().getState() == Player.PlayerState.LOCKED) {
            g.setClip(new Rectangle2D.Double(0, CINEMATIC_BORDER, Game.window().getResolution().getWidth(), Game.window().getResolution().getHeight() - CINEMATIC_BORDER * 2));
            Game.world().camera().setZoom(1.25f, 600);
        } else {
            Game.world().camera().setZoom(1, 600);
        }

        if (Game.world().environment() != null) {
            Game.world().environment().render(g);
        }

        super.render(g);

        ImageRenderer.render(g, Resources.images().get("textures/Golden Knight walking/Golden Knight walk/golden knight animation walk right edit_00004.png"),20,20);

        // render level name
        if (Game.world().environment() != null && levelNameTick != 0 && Game.screens().current().getName() == "Game") {
            long deltaTime = Game.time().since(levelNameTick);

            if (deltaTime > 1000 && deltaTime < LEVELNAME_DURATION) {
                // fade out status color
                final double fadeOutTime = 0.75 * LEVELNAME_DURATION;

                int alpha = 255;
                if (deltaTime > fadeOutTime) {
                    double fade = deltaTime - fadeOutTime;
                    alpha = (int) (255 - (fade / (LEVELNAME_DURATION - fadeOutTime)) * 255);
                    alpha = MathUtilities.clamp(alpha, 0, 255);
                }

                g.setColor(new Color(255, 255, 255, alpha));
            }
        }
    }

    @Override
    public void update() {
    }
}
