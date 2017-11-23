package blockerino;

import javax.swing.*;
import java.awt.*;

public class GameContainer extends JLayeredPane {

	private JLayeredPane gameContainer;

	public GameContainer() {
		setPreferredSize(new Dimension(Window.width, Window.height));
	}

	public JLayeredPane getGameContainer() {
		return gameContainer;
	}

	public void setGameContainer(JLayeredPane gameContainer) {
		this.gameContainer = gameContainer;
	}
}
