package blockerino.UI.gameOverlay;

import javax.swing.*;
import java.awt.*;

public class InventoryUI {
	private JPanel inventoryUI;
	private JButton testButton;

	public JPanel createUI() {
		inventoryUI = new JPanel();
		inventoryUI.setPreferredSize(new Dimension(200, 400));
		testButton = new JButton("testiiiiiiiiiiiiiiiing");
		inventoryUI.add(testButton);
		return inventoryUI;
	}

	public void JPanel() {
		//TODO Destroy UI.
	}

	public JPanel getInventoryUI() {
		return inventoryUI;
	}

	public void setInventoryUI(JPanel inventoryUI) {
		this.inventoryUI = inventoryUI;
	}
}
