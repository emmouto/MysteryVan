package View;

import Model.Player2;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.graphics.animation.AnimationController;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.util.Imaging;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Hud extends GuiComponent {
    private final BufferedImage HEART = Imaging.scale(Resources.images().get("src/main/resources/heart_full.png"),1.0);
    private final BufferedImage HEART_EMPTY = Imaging.scale(Resources.images().get("src/main/resources/heart_empty.png"), 0.5);

    private static final int PADDING = 10;
    //private final AnimationController useButtonAnimationController = new AnimationController(Resources.spritesheets().get("src/main/resources/textures/Golden Knight walking/spritesheet.png",true));
    //private final AnimationController arrowAnimationController;
    //private final AnimationController questAnimationController;

    protected Hud() {
        super(0, 0, Game.window().getResolution().getWidth(), Game.window().getResolution().getHeight());
        //Game.loop().attach(this.useButtonAnimationController);

        /*Spritesheet arrow = Resources.spritesheets().load(Resources.images().get("arrow.png"), "arrow", 23, 28);
        this.arrowAnimationController = new AnimationController(arrow);
        Game.loop().attach(this.arrowAnimationController);

        Spritesheet quest = Resources.spritesheets().load(Resources.images().get("quest.png"), "arrow", 23, 28);
        this.questAnimationController = new AnimationController(quest);
        Game.loop().attach(this.questAnimationController);*/
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.RED);

        if (Game.world().environment() == null || Player2.instance().isDead() || Player2.instance().getState() != Player2.PlayerState.CONTROLLABLE) {
            return;
        }
        //this.renderEnemyUI(g);
        this.renderHP(g);
        //this.renderUseButton(g);
        super.render(g);
        Player2.instance().render(g);
    }

    private void renderHP(Graphics2D g) {

        double y = Game.window().getResolution().getHeight() - PADDING * 2 - HEART.getHeight();
        double x = Game.window().getResolution().getWidth() / 2.0 - ((Player2.instance().getHitPoints().getMaxValue() * (HEART.getWidth() + PADDING) * 0.5) - PADDING);
        for (int i = 0; i < Player2.instance().getHitPoints().getMaxValue(); i++) {
            BufferedImage img = i < Player2.instance().getHitPoints().getCurrentValue() ? HEART : HEART_EMPTY;
            ImageRenderer.render(g, img, x + i * img.getWidth() + PADDING, y);
        }
    }


}
