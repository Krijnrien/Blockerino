package Server.entity.character.equipment.inventory;

import java.io.Serializable;

class Inventory implements Serializable {
	Inventory() {
		loadInventory();
	}

	private void loadInventory() {
		//TODO use serializable to load whole Inventory object from file.
	}

	//TODO Check if player already has backpack on. If yes, throw old backpack and its item on the ground.
	//dropItem(_backpack);
	//TODO Udpate UI?

}
