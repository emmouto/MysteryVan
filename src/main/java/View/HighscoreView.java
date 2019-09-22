package View;

import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;

public class HighscoreView extends Screen {
    public HighscoreView(String screenName) {
        super(screenName);
    }

    @Override
    public void render(final Graphics2D g) {
        g.setFont(Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",64f));
        g.setColor(Color.WHITE);
        TextRenderer.render(g, "HIGHSCORES", 300, 200);

        super.render(g);
    }
}
