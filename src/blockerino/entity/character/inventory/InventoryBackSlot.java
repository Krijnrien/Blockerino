package blockerino.entity.character.inventory;

import blockerino.items.container.ContainerItem;

public class InventoryBackSlot extends Inventory {

	private int isOccupied;
	private ContainerItem backpack;

	public int getIsOccupied() {
		return isOccupied;
	}

	public void setIsOccupied(int isOccupied) {
		this.isOccupied = isOccupied;
	}

	public ContainerItem getBackpack() {
		return backpack;
	}

	public void setBackpack(ContainerItem backpack) {
		this.backpack = backpack;
	}
}
