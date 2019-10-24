package View;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.util.Imaging;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The GameView class, which displays the game.
 *
 * @author Jonathan Carbol
 * @author Emma Pettersson
 * @version 0.1
 */
public class GameView extends GameScreen implements IUpdateable {
    private int HP;
    private int maxHP;
    private int score;

    private final BufferedImage HEART = Imaging.scale(Resources.images().get("src/main/resources/GameView/heart.png"),0.05);
    private final BufferedImage HEART_QUARTER = Imaging.scale(Resources.images().get("src/main/resources/GameView/heart1-4_2.png"),0.05);
    private final BufferedImage HEART_HALF = Imaging.scale(Resources.images().get("src/main/resources/GameView/heart1-2_2.png"),0.05);
    private final BufferedImage HEART_THREEQUARTER = Imaging.scale(Resources.images().get("src/main/resources/GameView/heart3-4_2.png"),0.05);
    private final BufferedImage HEART_EMPTY = Imaging.scale(Resources.images().get("src/main/resources/GameView/heart0_2.png"), 0.05);

    /**
     * The public constructor for the GameView class.
     *
     * @param screenName the name of the screen.
     */
    public GameView(String screenName) {
        super(screenName);
        Hud hud = new Hud();
        this.getComponents().add(hud);
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Attaches the GameView to the game loop.
     */
    @Override
    public void prepare() {
        super.prepare();

        // Play different music depeding on the game's difficulty.
        switch (GameManager.getSelectedDifficulty()) {
            case EASY:
                Game.audio().playMusic(GameManager.STAGE_1);
                break;
            case NORMAL:
                Game.audio().playMusic(GameManager.STAGE_2);
                break;
            case HARD:
                Game.audio().playMusic(GameManager.STAGE_3);
                break;
        }

        Game.loop().attach(this);
    }

    /**
     * Renders the GameView.
     *
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

    /**
     * An embedded class, Hud, used to display graphical components on the game screen.
     *
     * @author Jonathan Carbol
     * @version 0.1
     */
    public class Hud extends GuiComponent {
        Hud() {
            super(0, 0, Game.window().getResolution().getWidth(), Game.window().getResolution().getHeight());
        }

        /**
         * Renders the graphical components.
         *
         * @param g the graphic to be rendered.
         */
        @Override
        public void render(Graphics2D g) {
            renderHP(g);
            renderScore(g);
            super.render(g);
        }
        /**
         * Renders the HP of the player.
         *
         * @param g the graphic to be rendered.
         */
        private void renderHP(Graphics2D g){
            int PADDING = 10;
            double y = Game.window().getResolution().getHeight() - PADDING * 2 - HEART.getHeight();
            double x = Game.window().getResolution().getWidth() / 2.0 - ((Math.ceil(maxHP / 4.0)* (HEART.getWidth() + PADDING) * 0.5) - PADDING);
            boolean end = false;

            for (int i = 0; i < maxHP; i++) {
                if (i == 0){
                    i += 4;
                } else {
                    i += 3;
                }

                BufferedImage img;

                if (i <= HP) {
                    img = HEART;
                } else if(i <= maxHP + 4 && i >= HP && HP % 4 == 1 && !end) {
                    img = HEART_QUARTER;
                    end = true;
                } else if (i <= maxHP + 4 && i >= HP && HP % 4 == 2 && !end) {
                    img = HEART_HALF;
                    end = true;
                } else if (i <= maxHP + 4 && i >= HP && HP % 4 == 3 && !end) {
                    img = HEART_THREEQUARTER;
                    end = true;
                } else {
                    img = HEART_EMPTY;
                }

                ImageRenderer.render(g, img, x + (i * img.getWidth() / 4.0) + PADDING, y);
            }
        }

        /**
         * Renders the score of the player.
         *
         * @param g the graphic to be rendered.
         */
        private void renderScore(Graphics2D g){
            g.setFont(GameManager.PIXELED_BIG);
            g.setColor(Color.BLACK);

            String string = Integer.toString(score);
            TextRenderer.render(g,string,Game.window().getWidth() - 350,100);
        }
    }
}


