package View;

import Model.Player;
import Model.Player2;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.sound.Sound;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class InGameScreen extends Screen implements IUpdateable {

    private static final int LEVELNAME_DURATION = 7000;
    //private final BufferedImage NOTE_DEATH = Resources.images().get("died.png");
    //private final BufferedImage NOTE_SLAVES = Resources.images().get("slaves-killed.png");
    public static final Sound INGAME_MUSIC = Resources.sounds().get("src/main/resources/sounds/title_theme.mp3");
    public static final String NAME = "INGAME-SCREEN";
    private static final int CINEMATIC_BORDER = 100;
    //public static KeyboardMenu ingameMenu;
    //public static KeyboardMenu deathMenu;

    private Hud hud;
    public long lastPlayed;

    public static long levelNameTick;

    public InGameScreen() {
        super(NAME);
    }

    @Override
    public void prepare() {
        super.prepare();
        Game.loop().attach(this);

    }

    @Override
    public void suspend() {
        super.suspend();
        Game.loop().detach(this);
        Game.audio().stopMusic();
    }


    @Override
    protected void initializeComponents() {
        this.hud = new Hud();

        final double centerX = Game.window().getResolution().getWidth() / 2.0;
        final double centerY = Game.window().getResolution().getHeight() * 1 / 2;
        final double buttonWidth = 450;

       /* ingameMenu = new KeyboardMenu(centerX - buttonWidth / 2, centerY * 1.3, buttonWidth, centerY / 2, "Continue", "Exit");
        ingameMenu.onConfirm(c -> {
            switch (c.intValue()) {
                case 0:
                    GameManager.setState(GameState.INGAME);
                    break;
                case 1:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        });*/

       /* deathMenu = new KeyboardMenu(centerX - buttonWidth / 2, centerY * 1.3, buttonWidth, centerY / 2, "Retry", "Exit");
        deathMenu.onConfirm(c -> {
            switch (c.intValue()) {
                case 0:
                    Game.window().getRenderComponent().fadeOut(1000);

                    Game.loop().perform(1500, () -> {
                        // remove player before unloading the environment or the instance's
                        // animation controller will be disposed
                        Environment current = Game.world().environment();
                        Player.instance().setState(PlayerState.LOCKED);
                        current.remove(Player.instance());
                        Game.world().unloadEnvironment();
                        Game.world().loadEnvironment(new Environment(current.getMap().getName()));
                    });
                    break;
                case 1:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        });*/

        this.getComponents().add(this.hud);
        //this.getComponents().add(ingameMenu);
        //this.getComponents().add(deathMenu);
    }


    @Override
    public void render(Graphics2D g) {
        if (Player2.instance().getState() == Player2.PlayerState.LOCKED) {
            g.setClip(new Rectangle2D.Double(0, CINEMATIC_BORDER, Game.window().getResolution().getWidth(), Game.window().getResolution().getHeight() - CINEMATIC_BORDER * 2));
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            Game.world().camera().setZoom(1,600);
                            Player2.instance().setState(Player2.PlayerState.CONTROLLABLE);
                        }
                    },
                    5000
            );
        } else {
            Game.world().camera().setZoom(1, 600);
        }

        if (Game.world().environment() != null) {
            Game.world().environment().render(g);

        }

        super.render(g);

    }

    @Override
    public void update() {
        if (this.lastPlayed == 0) {
            Game.audio().playMusic(INGAME_MUSIC);
            this.lastPlayed = Game.loop().getTicks();
        }

    }
}
