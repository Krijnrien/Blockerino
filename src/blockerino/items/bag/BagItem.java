package blockerino.items.bag;

import blockerino.items.Item;

import java.util.Arrays;

public abstract class BagItem extends Item {

	public abstract int getSize();

	public abstract void setSize(int _size);

	private Item[] items = new Item[getSize()]; // Bag size of 20.

	public void indexDefaultBag() {
		Arrays.fill(getItems(), null);
	}

	public void emptyBag() {
		setItems(null);
	}

	public void addItemToBagOnPosition(int position, Item item) {
//		items.remove(position);
	}

	public void dropItemFromBag() {
		//TODO Remove item stack from items list
		//TODO Call drop item function (FloatingItem?)
	}

	//region Getters and Setters
	public Item[] getItems() {
		return items;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}

	//endregion


}
