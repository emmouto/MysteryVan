package View;

import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;

public class MenuView extends Screen {
    public MenuView(String screenName) {
        super(screenName);
    }

    @Override
    public void render(final Graphics2D g) {
        super.render(g);
        g.setFont(Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",32f));
        g.setColor(Color.RED);
        TextRenderer.render(g, "Test text", 100, 100);
    }
}
