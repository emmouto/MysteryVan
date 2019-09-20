package View;

import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.gui.ImageComponent;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.resources.Resources;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuView extends Screen {
    public MenuView(String screenName) {
        super(screenName);
    }

    @Override
    public void render(final Graphics2D g) {
        super.render(g);

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/main/resources/temp/startpage.png"));
        } catch (IOException e) {
        }

        g.drawImage(img, 0, 0, null);

        g.setFont(Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",64f));
        g.setColor(Color.GREEN);
        TextRenderer.render(g, "CHALMERSFORCE", 224, 200);


    }
}
