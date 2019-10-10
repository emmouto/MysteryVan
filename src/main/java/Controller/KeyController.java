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
 * @author
 * @version
 */
public class KeyController implements KeyListener, IUpdateable {

    /**
     * Assigning the variable keys to actual letters
     *
     * @param pc
     */
    public KeyController(PlayerController pc) {
        initKeyController();
    }

    private void initKeyController(){
        bind(KeyEvent.VK_W, Key.up);
        bind(KeyEvent.VK_A, Key.left);
        bind(KeyEvent.VK_S, Key.down);
        bind(KeyEvent.VK_D, Key.right);
        bind(KeyEvent.VK_SPACE, Key.special);
        bind(KeyEvent.VK_P, Key.pause);

        Input.keyboard().onKeyPressed(e ->{
            keyPressed(e);
        });
        Input.keyboard().onKeyReleased(e->{
            keyReleased(e);
        });

    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            other[e.getExtendedKeyCode()] = true;
            keyBindings.get(e.getKeyCode()).isDown = true;
        }catch (Exception n){

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            other[e.getExtendedKeyCode()] = false;
            keyBindings.get(e.getKeyCode()).isDown = false;
        }catch (Exception n){

        }
    }

    public boolean isKeyBinded(int extendedKey) {
        return keyBindings.containsKey(extendedKey);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }


    public void bind(Integer keyCode, Key key) {
        keyBindings.put(keyCode, key);
    }

    public void releaseAll() {
        for (Key key : keyBindings.values()) {
            key.isDown = false;
        }
    }

    public HashMap<Integer, Key> keyBindings = new HashMap<Integer, Key>();

    public static boolean other[] = new boolean[256];

    @Override
    public void update() {
    }
}