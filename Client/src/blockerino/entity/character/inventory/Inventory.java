package blockerino.entity.character.inventory;

import blockerino.items.Item;
import blockerino.items.container.ContainerItem;

import java.io.Serializable;

public class Inventory implements Serializable {
	public InventoryBackSlot backpackSlot;

	public Inventory() {
		loadInventory();
	}

	private void loadInventory() {
		//TODO use serializable to load whole Inventory object from file.
	}

	private void setBackpack(ContainerItem _backpack) {
		//TODO Check if player already has backpack on. If yes, throw old backpack and its item on the ground.
		//dropItem(_backpack);
		//TODO Udpate UI?
		this.backpackSlot.setBackpack(_backpack);
	}

	private Item[] getBackpackContent() {
		return this.backpackSlot.getBackpack().getItems();
	}

}
