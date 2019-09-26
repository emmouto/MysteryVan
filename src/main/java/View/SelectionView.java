package View;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;

/**
 * Lets the player choose a character and a difficulty level.
 *
 * @author Emma Pettersson
 */
public class SelectionView extends Screen implements IUpdateable {
    public SelectionView(String screenName) {
        super(screenName);
    }

    @Override
    protected void initializeComponents() {

    }

    @Override
    public void prepare() {

        super.prepare();

        Game.loop().attach(this);
    }

    @Override
    public void render(final Graphics2D g) {
        g.setFont(Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",64f));
        g.setColor(Color.WHITE);
        TextRenderer.render(g, "SELECTION", 300, 200);

        super.render(g);
    }

    @Override
    public void update() {

    }
}
