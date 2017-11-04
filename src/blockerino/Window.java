package blockerino;

import javax.swing.JFrame;

public class Window extends JFrame {

    private JFrame jFrame;

    public Window() {
        jFrame = new JFrame("blockerino2");

        jFrame.setTitle("Blockerino");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(new GamePanel(1280, 720));
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setFocusable(true);
        jFrame.requestFocus();
    }
}
