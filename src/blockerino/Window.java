package blockerino;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Window extends JFrame {

    private JFrame jFrame;

    public JFrame getJFrame() {
        return this.jFrame;
    }

    public void setJFrame(JFrame _jFrame) {
        this.jFrame = _jFrame;
    }

    public void createWindow() {
        JFrame jFrame = new JFrame("blockerino2");
        jFrame.setTitle("Blockerino");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(new GamePanel(1280, 720));
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setFocusable(true);
        jFrame.requestFocus();
        jFrame.addComponentListener(new ResizeListener());
        setJFrame(jFrame);
    }

    class ResizeListener extends ComponentAdapter {
        public void componentResized(ComponentEvent e) {
            GamePanel.width = e.getComponent().getWidth();
            GamePanel.height = e.getComponent().getHeight();
        }
    }
}
