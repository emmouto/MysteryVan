package Controller;

import Model.GameManager;
import View.MenuView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.Menu;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.sound.Sound;

/**
 * Controls for the (non-ingame) Screens.
 *
 * @author Emma Pettersson
 * @author Jennifer Krogh
 */
public class ScreenController extends Menu {
    private static final int DELAY = 180;

    private final List<Consumer<Integer>> confirmConsumer;
    private int currentFocus = -1;

    private static long lastInput;

    /**
     * Constructor for the ScreenController.
     *
     * @param x
     *      ....
     * @param y
     *      ...
     * @param width
     *      ...
     * @param height
     *      ...
     * @param items
     *      ...
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

        this.onConfirm(c -> {
            disableController();

            switch (c) {
                case 0:
                     MenuView.showHighscore();
                    break;
                case 1:
                    MenuView.startGame();
                    break;
                case 2:
                    MenuView.exit();
                    break;
                default:
                    break;
            }
        });
    }

    private boolean inputIsLocked() {
        // Disable Screen controls if view has changed.
        if (this.isSuspended() || !this.isVisible() || !this.isEnabled()) {
            return true;
        }

        return Game.time().since(lastInput) < DELAY;
    }

    /**
     *
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
     * ...
     *
     * @param cons
     *      ...
     */
    public void onConfirm(Consumer<Integer> cons) {
        this.confirmConsumer.add(cons);
    }

    private void confirm() {
        for (Consumer<Integer> cons : this.confirmConsumer) {
            cons.accept(this.currentFocus);
        }
    }

    private void decFocus() {
        this.currentFocus = Math.floorMod(--this.currentFocus, this.getCellComponents().size());
        this.updateFocus();
    }

    public void incFocus() {
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
            Game.audio().playSound(GameManager.SELECT_SOUND);
        }
    }

    public void fixMenuView() {
        // Places the MenuView buttons in their correct places.
        this.getCellComponents().get(0).setX(210);
        this.getCellComponents().get(0).setY(460);
        this.getCellComponents().get(1).setX(550);
        this.getCellComponents().get(1).setY(460);
        this.getCellComponents().get(2).setX(880);
        this.getCellComponents().get(2).setY(460);
    }

    public void disableController() {
        this.setEnabled(false);
    }
}
