package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeyTest {

    @Test
    void keyShouldToggleCurrentKeyState() {
        Key k = new Key();
        k.isDown = true;

        k.toggle();

        assertFalse(k.isDown);
    }
}