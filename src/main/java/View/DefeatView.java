package View;

import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DefeatView extends Screen {

    private static final BufferedImage defeatBG = Resources.images().get("src/main/resources/DefeatView/DefeatBG.png");

    public DefeatView(String screenName) {
        super(screenName);
    }

    @Override
    public void render(final Graphics2D g) {
        ImageRenderer.render(g, defeatBG, 0, 0);

        g.setFont(Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",64f));
        g.setColor(Color.RED);
        TextRenderer.render(g, "DEFEATED", 350, 200);

        super.render(g);
    }
}

