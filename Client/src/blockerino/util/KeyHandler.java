package blockerino.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import blockerino.GamePanel;

public class KeyHandler implements KeyListener {

    private static List<Key> keys = new ArrayList<>();

    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();
    public Key menu = new Key();
    public Key enter = new Key();
    public Key escape = new Key();

    public KeyHandler(GamePanel _gamePanel) {
        _gamePanel.addKeyListener(this);
    }

    private void toggle(KeyEvent e, boolean pressed) {
        if (e.getKeyCode() == KeyEvent.VK_W) up.hold(pressed);
        if (e.getKeyCode() == KeyEvent.VK_S) down.hold(pressed);
        if (e.getKeyCode() == KeyEvent.VK_A) left.hold(pressed);
        if (e.getKeyCode() == KeyEvent.VK_D) right.hold(pressed);
    }

    private void pressed(KeyEvent e) {
        //if (e.getKeyCode() == KeyEvent.VK_E) menu.toggle();
        // if (e.getKeyCode() == KeyEvent.VK_ENTER) enter.toggle();
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) escape.toggle();
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // Not usable as always returns keyCode 0 / VK_UNDEFINED.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        toggle(e, true);
        pressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggle(e, false);
    }

    public class Key {
        public boolean down;
        public boolean toggle;

        Key() {
            keys.add(this);
        }

        void toggle() {
            toggle = !toggle;
        }

        void hold(boolean _pressed) {
            if (_pressed != down) {
                down = _pressed;
            }
        }

    }
}
