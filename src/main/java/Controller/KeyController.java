package Controller;

import Model.Key;

import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.input.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * ...
 *
 * @author Jennifer Krogh
 * @author Jonathan Carbol
 * @version 0.1
 */
public class KeyController implements KeyListener, IUpdateable {
    private HashMap<Integer, Key> keyBindings = new HashMap<Integer, Key>();
    private static boolean other[] = new boolean[256];

    /**
     * Assigning the variable keys to actual letters
     *
     * @param playerController
     */
    KeyController(PlayerController playerController) {
        initKeyController();
    }

    private void initKeyController(){
        bind(KeyEvent.VK_W, Key.up);
        bind(KeyEvent.VK_UP, Key.up);
        bind(KeyEvent.VK_A, Key.left);
        bind(KeyEvent.VK_LEFT, Key.left);
        bind(KeyEvent.VK_S, Key.down);
        bind(KeyEvent.VK_DOWN, Key.down);
        bind(KeyEvent.VK_D, Key.right);
        bind(KeyEvent.VK_RIGHT, Key.right);
        bind(KeyEvent.VK_SPACE, Key.attack);
        bind(KeyEvent.VK_P, Key.pause);
        bind(KeyEvent.VK_ENTER, Key.enter);

        Input.keyboard().onKeyPressed(this::keyPressed);
        Input.keyboard().onKeyReleased(this::keyReleased);

    }

    /**
     * Checks if key is pressed.
     *
     * @param e the key to be checked.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        try {
            other[e.getExtendedKeyCode()] = true;
            keyBindings.get(e.getKeyCode()).isDown = true;
        } catch (Exception n) {

        }
    }

    /**
     * Checks if key is release
     *
     * @param e the key to be checked.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        try {
            other[e.getExtendedKeyCode()] = false;
            keyBindings.get(e.getKeyCode()).isDown = false;
        }catch (Exception n){

        }
    }

    /**
     *
     * @param extendedKey
     * @return if the key is binded or not.
     */
    public boolean isKeyBinded(int extendedKey) {
        return keyBindings.containsKey(extendedKey);
    }

    /**
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     *
     * @param keyCode
     * @param key
     */
    public void bind(Integer keyCode, Key key) {
        keyBindings.put(keyCode, key);
    }

    /**
     *
     */
    public void releaseAll() {
        for (Key key : keyBindings.values()) {
            key.isDown = false;
        }
    }

    /**
     *
     */
    @Override
    public void update() {
    }
}