package View;

import Controller.KeyboardMenu;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The game's title screen.
 *
 * @author Emma Pettersson
 */
public class MenuView extends Screen implements IUpdateable {
    private static final BufferedImage BG = Resources.images().get("src/main/resources/menu/bg.png");
    private static final BufferedImage CLOUDS = Resources.images().get("src/main/resources/menu/clouds.png");

    private KeyboardMenu mainMenu;

    public MenuView(String screenName) {
        super(screenName);
    }

    private void exit() {
        System.exit(0);
    }

    @Override
    protected void initializeComponents() {
        final double centerX = Game.window().getResolution().getWidth() / 2.0;
        final double centerY = Game.window().getResolution().getHeight() * 1 / 2;
        final double buttonWidth = 200;

        // TODO make the menu horizontal
        this.mainMenu = new KeyboardMenu(centerX - buttonWidth / 2, centerY * 1.3, buttonWidth, centerY / 2, "Highscore", "Play", "Exit");
        //this.mainMenu.setFont(Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",24f)); // TODO make the font change work
        this.getComponents().add(this.mainMenu);

        this.mainMenu.onConfirm(c -> {
            switch (c.intValue()) {
                case 0:
                    this.startGame();
                    break;
                case 2:
                    this.exit();
                    break;
                default:
                    break;
            }
        });
    }

    @Override
    public void prepare() {
        this.mainMenu.setEnabled(true);
        super.prepare();
        Game.loop().attach(this);
        Game.graphics().setBaseRenderScale(6f * Game.window().getResolutionScale());
        this.mainMenu.incFocus();
    }

    @Override
    public void render(final Graphics2D g) {
        // TODO animate the clouds
        ImageRenderer.render(g, CLOUDS, 0, 0);
        ImageRenderer.render(g, BG, 0, 0);

        g.setFont(Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",64f));
        g.setColor(Color.BLACK);
        TextRenderer.render(g, "CHALMERSFORCE", 224, 200);

        super.render(g);
    }


    private void startGame() {
        this.mainMenu.setEnabled(false);
        //Game.audio().playSound("confirm.ogg"); // TODO fix audio
        Game.window().getRenderComponent().fadeOut(2500);

        /*Game.loop().perform(3500, () -> {
            Game.screens().display("INGAME-SCREEN");
            Game.world().loadEnvironment(GameManager.START_LEVEL);
            GameManager.setState(GameState.INGAME);
        });
         */
    }


    @Override
    public void suspend() {
        super.suspend();
        Game.loop().detach(this);
        Game.audio().stopMusic();
    }

    @Override
    public void update() {

    }
}
