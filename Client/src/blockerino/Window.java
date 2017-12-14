package blockerino;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Window extends JFrame {

    public static int width;
    public static int height;

    private JPanel gamePanel;
    private JLayeredPane layeredPane;

    public void createWindow() {
        width = 1280;
        height = 720;

        setTitle("Blockerino");
        setPreferredSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        layeredPane = getLayeredPane();

        gamePanel = new GamePanel();
        gamePanel.setBounds(0, 0, width, height);

        layeredPane.add(gamePanel, 1);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        //setFocusable(true);
        //requestFocus();
        addComponentListener(new ResizeListener());
    }

    class ResizeListener extends ComponentAdapter {
        public void componentResized(ComponentEvent e) {
            width = e.getComponent().getWidth();
            height = e.getComponent().getHeight();
        }
    }
}
