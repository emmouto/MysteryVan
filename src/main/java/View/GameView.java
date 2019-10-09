package View;

import Controller.PlayerController;
import Model.Player;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.util.Imaging;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Jonathan Carbol
 * The GameView class, which displays the game.
 */
public class GameView extends GameScreen implements IUpdateable {

    private Hud hud;
    private int HP;
    private int maxHP;

    private static int PADDING =10;
    private final BufferedImage HEART = Imaging.scale(Resources.images().get("src/main/resources/heart.png"),0.05);
    private final BufferedImage HEART_QUARTER = Imaging.scale(Resources.images().get("src/main/resources/heart1-4_2.png"),0.05);
    private final BufferedImage HEART_HALF = Imaging.scale(Resources.images().get("src/main/resources/heart1-2_2.png"),0.05);
    private final BufferedImage HEART_THREEQUARTER = Imaging.scale(Resources.images().get("src/main/resources/heart3-4_2.png"),0.05);
    private final BufferedImage HEART_EMPTY = Imaging.scale(Resources.images().get("src/main/resources/heart0_2.png"), 0.05);

    /**
     * The public constructor for the GameView class.
     * @param screenName the name of the screen.
     */
    public GameView(String screenName) {
        super(screenName);
        this.hud = new Hud();
        this.getComponents().add(this.hud);
    }

    /**
     * Attaches the GameView to the game loop.
     */
    @Override
    public void prepare(){
        super.prepare();
        Game.loop().attach(this);
    }

    /**
     * Renders the GameView.
     * @param g the graphical item to be rendered.
     */
    @Override
    public void render(Graphics2D g) {
        super.render(g);
    }

    /**
     * Updates the GameView.
     */
    @Override
    public void update() {

    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    /**
     * @author Jonathan Carbol
     * An inbedded class Hud, used to display graphical components on the game screen.
     */
    public class Hud extends GuiComponent {

        /**
         * The protected constructor of the Hud class.
         */
        protected Hud() {
            super(0, 0, Game.window().getResolution().getWidth(), Game.window().getResolution().getHeight());
        }

        /**
         * Renders the graphical components.
         * @param g the graphic to be rendered.
         */
        @Override
        public void render(Graphics2D g) {
            renderHP(g);
            super.render(g);
        }

        /**
         * Renders the HP of the player.
         * @param g the graphic to be rendered.
         */
        private void renderHP(Graphics2D g){

            double y = Game.window().getResolution().getHeight() - PADDING * 2 - HEART.getHeight();
            double x = Game.window().getResolution().getWidth() / 2.0 - ((Math.ceil(maxHP/4)* (HEART.getWidth() + PADDING) * 0.5) - PADDING);
            boolean end = false;
            for (int i = 0; i < maxHP; i++) {
                if(i == 0){
                    i += 4;
                }else{
                    i += 3;
                }
                BufferedImage img;
                if(i <= HP){
                    img = HEART;
                }else if(i <= maxHP+4 && i >= HP && HP % 4 == 1 && end == false){
                    img = HEART_QUARTER;
                    end = true;
                }else if(i <= maxHP+4 && i >= HP && HP % 4 == 2 && end == false){
                    img = HEART_HALF;
                    end = true;
                }else if(i <= maxHP+4 && i >= HP && HP % 4 == 3 && end == false) {
                    img = HEART_THREEQUARTER;
                    end = true;
                }else{
                    img = HEART_EMPTY;
                }
                ImageRenderer.render(g, img, x + (i * img.getWidth()/4) + PADDING, y);
            }
        }
    }


}


