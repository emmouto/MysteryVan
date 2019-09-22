package Controller;

import java.awt.Color;
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
 *
 */
public class MenuController extends Menu {
    private static final Sound SETTING_CHANGE_SOUND = Resources.sounds().get("src/main/resources/audio/sfx/menu_sound.wav");
    private static final int MENU_DELAY = 180;

    private final List<Consumer<Integer>> confirmConsumer;
    private int currentFocus = -1;

    private static long lastMenuInput;

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param items
     */
    public MenuController(double x, double y, double width, double height, String... items) {
        super(x, y, width, height, items);
        this.confirmConsumer = new CopyOnWriteArrayList<>();

        Input.keyboard().onKeyReleased(e -> {
            if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_E) {
                if (this.menuInputIsLocked()) {
                    return;
                }

                this.confirm();
                lastMenuInput = Game.time().now();
            }
        });

        Input.keyboard().onKeyPressed(KeyEvent.VK_LEFT, e -> {
            if (this.menuInputIsLocked()) {
                return;
            }

            decFocus();
        });

        Input.keyboard().onKeyPressed(KeyEvent.VK_RIGHT, e -> {
            if (this.menuInputIsLocked()) {
                return;
            }

            incFocus();
        });

    }

    private boolean menuInputIsLocked() {
        // disable menu if the game has started
        if (this.isSuspended() || !this.isVisible() || !this.isEnabled()) {
            return true;
        }

        return Game.time().since(lastMenuInput) < MENU_DELAY;
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
            comp.setFont(Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",24f));
            comp.getAppearance().setForeColor(Color.WHITE);
            comp.getAppearance().setTransparentBackground(true);
            comp.getAppearanceHovered().setTransparentBackground(true);
            comp.getAppearanceHovered().setForeColor(Color.BLACK);
        });

        // TODO maybe make this code not awful..?
        this.getCellComponents().get(0).setX(210);
        this.getCellComponents().get(0).setY(460);
        this.getCellComponents().get(1).setX(550);
        this.getCellComponents().get(1).setY(460);
        this.getCellComponents().get(2).setX(880);
        this.getCellComponents().get(2).setY(460);
    }

    /**
     *
     * @param cons
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

        lastMenuInput = Game.time().now();

        if (this.isVisible()) {
            Game.audio().playSound(SETTING_CHANGE_SOUND);
        }
    }
}
