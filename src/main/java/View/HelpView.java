package View;


import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;

public class HelpView extends Screen implements IUpdateable {

    public HelpView(String screenName) {
        super(screenName);
    }

    public void prepare() {
        Game.audio().playMusic(Resources.sounds().get("src/main/resources/audio/music/stage_select.ogg"));

        super.prepare();

        Game.loop().attach(this);
    }

    public void render(final Graphics2D g) {
        g.setFont(Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",64f));
        g.setColor(Color.WHITE);
        TextRenderer.render(g, "HELP", 330, 200);

        super.render(g);
    }

    @Override
    public void update() {

    }
}
