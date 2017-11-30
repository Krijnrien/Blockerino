package Server.game.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class MouseHandler implements MouseListener, MouseMotionListener {

    private static List<MouseHandler.Mouse> mouses = new ArrayList<>();

    private static int mouseX = -1;
    private static int mouseY = -1;

    public MouseHandler.Mouse button1 = new MouseHandler.Mouse();
    public MouseHandler.Mouse button2 = new MouseHandler.Mouse();


    public int getMouseX() {
        return mouseX;
    }

    public void setMouseX(int _mouseX) {
        mouseX = _mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseY(int _mouseY) {
        mouseY = _mouseY;
    }

    private void toggle(MouseEvent e, boolean pressed) {
        if (e.getButton() == MouseEvent.BUTTON1) button1.hold(pressed);
        if (e.getButton() == MouseEvent.BUTTON2) button2.hold(pressed);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // keyhandler always returns 0 on clicked, assume mouse does too.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        toggle(e, true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        toggle(e, false);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public class Mouse {
        public boolean down;

        Mouse() {
            mouses.add(this);
        }

        void hold(boolean _pressed) {
            if (_pressed != down) {
                down = _pressed;
            }
        }

    }

}
