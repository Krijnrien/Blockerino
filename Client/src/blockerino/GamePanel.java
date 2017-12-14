package blockerino;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

	private JPanel gamePanel;

	public GamePanel() {
		setPreferredSize(new Dimension(Window.width, Window.height));

		System.out.println("Starting client kryo"); //TODO add proper logging
		CommandClient commandClient = new CommandClient("127.0.0.1", 32064);
		Thread connectionThread = new Thread(commandClient, "connectionThread");
		connectionThread.start();
	}

	public JPanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(JPanel gamePanel) {
		this.gamePanel = gamePanel;
	}
}
