package blockerino;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Window extends JFrame {

	public static int width;
	public static int height;

	private JFrame jFrame;
	private JLayeredPane gameContainer;
	private JPanel gamePanel;

	void createWindow() {
		width = 1280;
		height = 720;

		jFrame = new JFrame("Blockerino");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gameContainer = new GameContainer();

		gamePanel = new GamePanel();
		gameContainer.add(gamePanel, 1);

		jFrame.setContentPane(gameContainer);
		jFrame.pack();
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
		jFrame.setFocusable(true);
		jFrame.requestFocus();
		jFrame.addComponentListener(new ResizeListener());
	}

	class ResizeListener extends ComponentAdapter {
		public void componentResized(ComponentEvent e) {
			width = e.getComponent().getWidth();
			height = e.getComponent().getHeight();
		}
	}
}
