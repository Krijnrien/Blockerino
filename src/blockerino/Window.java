package blockerino;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Window extends JFrame {

    void createWindow() {
        JFrame jFrame = new JFrame("blockerino2");
        jFrame.setTitle("Blockerino");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel gamePanel = new GamePanel(1280, 720);
        jFrame.setContentPane(gamePanel);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setFocusable(true);
        jFrame.requestFocus();
        jFrame.addComponentListener(new ResizeListener());
    }


    class ResizeListener extends ComponentAdapter {
        public void componentResized(ComponentEvent e) {
            GamePanel.width = e.getComponent().getWidth();
            GamePanel.height = e.getComponent().getHeight();
        }
    }
}
