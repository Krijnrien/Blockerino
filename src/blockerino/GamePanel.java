package blockerino;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

	private JPanel gamePanel;

	public GamePanel() {
		setPreferredSize(new Dimension(Window.width, Window.height));
		GameLoop gameLoop = new GameLoop(this);
		Thread t = new Thread(gameLoop, "GameThread");
		t.start();
	}

	public JPanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(JPanel gamePanel) {
		this.gamePanel = gamePanel;
	}
}
