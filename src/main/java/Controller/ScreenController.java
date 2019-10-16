package Controller;

import View.GameManager;
import View.DefeatView;
import View.HelpView;
import View.HighScoreView;
import View.MenuView;

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

    private final List<Consumer<Integer>> confirmConsumer;
    private int currentFocus = -1;

    private static long lastInput;

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

    private void onConfirm(Consumer<Integer> consumer) {
        this.confirmConsumer.add(consumer);
    }

    private void confirm() {
        changeView();

        for (Consumer<Integer> cons : this.confirmConsumer) {
            cons.accept(this.currentFocus);
        }
    }

    private void changeView() {
        if (GameManager.getState() == GameManager.GameState.TITLE_SCREEN) {
            this.onConfirm(c -> {
                switch (c) {
                    case 0:
                        GameManager.setState(GameManager.GameState.HIGH_SCORE_SCREEN);
                        MenuView.showHighScoreScreen();
                        break;
                    case 1:
                        GameManager.setState(GameManager.GameState.HELP_SCREEN);
                        MenuView.showHelpScreen();
                        break;
                    case 2:
                        MenuView.exit();
                        break;
                    default:
                        break;
                }
            });
        } else if (GameManager.getState() == GameManager.GameState.DEFEAT_SCREEN) {
            GameManager.setState(GameManager.GameState.HIGH_SCORE_SCREEN);
            DefeatView.showHighScore();
        } else if (GameManager.getState() == GameManager.GameState.INGAME_PAUSE) {
            GameManager.setState(GameManager.GameState.TITLE_SCREEN);
            HighScoreView.showMenu();
        } else if (GameManager.getState() == GameManager.GameState.HIGH_SCORE_SCREEN) {
            GameManager.setState(GameManager.GameState.TITLE_SCREEN);
            HighScoreView.showMenu();
        } else if (GameManager.getState() == GameManager.GameState.HELP_SCREEN) {
            GameManager.setState(GameManager.GameState.SELECTION_SCREEN);
            HelpView.goToSelect();
        }
    }

    private void decFocus() {
        this.currentFocus = Math.floorMod(--this.currentFocus, this.getCellComponents().size());
        this.updateFocus();
    }

    private void incFocus() {
        this.currentFocus = ++this.currentFocus % this.getCellComponents().size();
        this.updateFocus();
    }

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
}
