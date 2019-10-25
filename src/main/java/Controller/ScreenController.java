package Controller;

import Model.Key;
import View.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.Menu;
import de.gurkenlabs.litiengine.input.Input;

/**
 * Controls for the (non-ingame) Screens.
 *
 * @author Emma Pettersson
 * @author Jennifer Krogh
 * @version 0.1
 */
public class ScreenController extends Menu {
    private static final int DELAY = 180;
    private static long lastInput;

    private final List<Consumer<Integer>> confirmConsumer;
    private int currentFocus = -1;

    /**
     * Constructor for the ScreenController (based on Menu).
     *
     * @param x the Menu's x coordinate.
     * @param y the Menu's y coordinate.
     * @param width the Menu's width.
     * @param height the Menu's height.
     * @param items the items in the Menu.
     */
    public ScreenController(double x, double y, double width, double height, String... items) {
        super(x, y, width, height, items);
        this.confirmConsumer = new CopyOnWriteArrayList<>();

        Input.keyboard().onKeyReleased(e -> {
            if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_E) {
                if (this.inputIsLocked()) {
                    return;
                }

                this.confirm();
                lastInput = Game.time().now();
            }
        });

        Input.keyboard().onKeyPressed(e -> {
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                if (this.inputIsLocked()) {
                    return;
                }

                decFocus();
            }
        });

        Input.keyboard().onKeyPressed(e -> {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                if (this.inputIsLocked()) {
                    return;
                }

                incFocus();
            }
        });
    }

    /**
     * Checks if the input is locked.
     * @return true if input is locked else false.
     */
    private boolean inputIsLocked() {
        if (this.isSuspended() || !this.isVisible() || !this.isEnabled()) {
            return true;
        }

        return Game.time().since(lastInput) < DELAY;
    }

    /**
     * Prepare the GuiComponent and all its child Components.
     * (Makes the GuiComponent visible and adds mouse listeners).
     * This is, for example, done right before switching to a new screen.
     */
    @Override
    public void prepare() {
        super.prepare();
        this.setForwardMouseEvents(false);
        this.getCellComponents().forEach(comp -> comp.setForwardMouseEvents(false));

        if (!this.getCellComponents().isEmpty()) {
            this.currentFocus = 0;
            this.getCellComponents().get(0).setHovered(true);
        }

        this.getCellComponents().forEach(comp -> {
            comp.setFont(GameManager.PIXELED_SMALL);
            comp.getAppearance().setForeColor(Color.WHITE);
            comp.getAppearance().setTransparentBackground(true);
            comp.getAppearanceHovered().setTransparentBackground(true);
            comp.getAppearanceHovered().setForeColor(Color.BLACK);
        });
    }

    /**
     * Adds a consumer when its confirmed.
     * @param consumer the consumer to be added when its been confirmed.
     */
    private void onConfirm(Consumer<Integer> consumer) {
        this.confirmConsumer.add(consumer);
    }

    /**
     * Confirms a choice.
     */
    private void confirm() {
        updateView();

        for (Consumer<Integer> cons : this.confirmConsumer) {
            cons.accept(this.currentFocus);
        }
    }

    /**
     * Changes the currently visible Screen.
     *
     * @param screenName the name of the Screen.
     * @param fadeTime the time for the previous Screen to fade out, and the new one to fade in.
     */
    void changeScreen(String screenName, int fadeTime) {
        Game.audio().playSound(GameManager.SELECT_SOUND);
        Game.window().getRenderComponent().fadeOut(fadeTime);
        if (fadeTime == 1500) { Game.audio().fadeMusic(150); }

        Game.loop().perform(fadeTime, () -> {
            Game.window().getRenderComponent().fadeIn(fadeTime);
            Game.screens().display(screenName);
        });
    }

    /**
     * Changes the View depending on the current GameState.
     */
    private void updateView() {
        if (GameManager.getState() == GameManager.GameState.TITLE_SCREEN) {
            this.onConfirm(c -> {
                switch (c) {
                    case 0:
                        GameManager.setState(GameManager.GameState.HIGH_SCORE_SCREEN);
                        Game.screens().remove(Game.screens().get("Highscore"));
                        Game.screens().add(new HighScoreView("Highscore"));
                        changeScreen("HighScore", 500);
                        break;
                    case 1:
                        GameManager.setState(GameManager.GameState.HELP_SCREEN);
                        Game.screens().remove(Game.screens().get("Help"));
                        Game.screens().add(new HelpView("Help"));
                        changeScreen("Help", 1500);
                        break;
                    case 2:
                        exit();
                        break;
                    default:
                        break;
                }
            });
        } else if (GameManager.getState() == GameManager.GameState.DEFEAT_SCREEN) {
            GameManager.setState(GameManager.GameState.HIGH_SCORE_SCREEN);
            Game.screens().remove(Game.screens().get("Highscore"));
            Game.screens().add(new HighScoreView("Highscore"));
            changeScreen("HighScore", 500);
        } else if (GameManager.getState() == GameManager.GameState.INGAME_PAUSE) {
            GameManager.setState(GameManager.GameState.TITLE_SCREEN);
            Game.screens().remove(Game.screens().get("Menu"));
            Game.screens().add(new MenuView("Menu"));
            changeScreen("Menu", 500);
        } else if (GameManager.getState() == GameManager.GameState.HIGH_SCORE_SCREEN) {
            GameManager.setState(GameManager.GameState.TITLE_SCREEN);
            Game.screens().remove(Game.screens().get("Menu"));
            Game.screens().add(new MenuView("Menu"));
            changeScreen("Menu", 500);
        } else if (GameManager.getState() == GameManager.GameState.HELP_SCREEN) {
            GameManager.setState(GameManager.GameState.SELECTION_SCREEN);
            changeScreen("Selection", 500);
        }
    }

    /**
     * Changes the focus down in a focus list.
     */
    private void decFocus() {
        this.currentFocus = Math.floorMod(--this.currentFocus, this.getCellComponents().size());
        this.updateFocus();
    }

    /**
     * Changes the focus up in a focus list.
     */
    private void incFocus() {
        this.currentFocus = ++this.currentFocus % this.getCellComponents().size();
        this.updateFocus();
    }

    /**
     * Updates the focus.
     */
    private void updateFocus() {
        this.setCurrentSelection(this.currentFocus);

        for (int i = 0; i < this.getCellComponents().size(); i++) {
            this.getCellComponents().get(i).setHovered(i == this.currentFocus);
        }

        lastInput = Game.time().now();

        if (this.isVisible() && Game.time().now() > 10){
            Game.audio().playSound(GameManager.MENU_SOUND);
        }
    }

    /**
     * Places the MenuView buttons in their correct places.
     */
    public void fixMenuView() {
        this.getCellComponents().get(0).setX(210);
        this.getCellComponents().get(0).setY(460);
        this.getCellComponents().get(1).setX(550);
        this.getCellComponents().get(1).setY(460);
        this.getCellComponents().get(2).setX(880);
        this.getCellComponents().get(2).setY(460);

        incFocus();
    }

    /**
     * Pauses the game when P is pressed.
     */
    void changeToPause(){
        if (Key.pause.isDown && (GameManager.getState() == GameManager.GameState.INGAME)) {
            GameManager.setState(GameManager.GameState.INGAME_PAUSE);
            changeScreen("Pause", 500);
        } else if (Key.pause.isDown && (GameManager.getState() == GameManager.GameState.INGAME_PAUSE)) {
            GameManager.setState(GameManager.GameState.INGAME);
            changeScreen("Game", 500);
        }
    }

    /**
     * Exits the game.
     */
    private static void exit() {
        Game.audio().playSound(GameManager.SELECT_SOUND);
        System.exit(0);
    }
}
