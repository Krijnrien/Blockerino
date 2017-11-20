package blockerino.items.bag.Backpack;

import blockerino.items.Item;
import blockerino.items.bag.BagItem;

public class AdventureBackpack extends BagItem {

	private int size;
	private Item[] items = new Item[size]; // Bag size of 20.


	public int getSize() {
		return size;
	}

	public void setSize(int _size) {
		this.size = _size;
	}

}
