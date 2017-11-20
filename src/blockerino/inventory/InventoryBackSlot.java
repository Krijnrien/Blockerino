package blockerino.inventory;

import blockerino.items.bag.BagItem;

public class InventoryBackSlot extends Inventory {

	private int isOccupied;
	private BagItem backpack;

	public int getIsOccupied() {
		return isOccupied;
	}

	public void setIsOccupied(int isOccupied) {
		this.isOccupied = isOccupied;
	}

	public BagItem getBackpack() {
		return backpack;
	}

	public void setBackpack(BagItem backpack) {
		this.backpack = backpack;
	}
}
