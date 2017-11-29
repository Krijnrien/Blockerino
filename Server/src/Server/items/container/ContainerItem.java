package Server.items.container;

import Server.items.Item;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@XmlRootElement
public class ContainerItem extends Item {

    private int size;
    private Item[] items = new Item[size]; // Bag size

    public void indexDefaultBag() {
        Arrays.fill(getItems(), null);
    }

    public void emptyBag() {
        setItems(null);
    }

    public void addItemToBagOnPosition(int position, Item item) {
//		item.remove(position);
    }

    public void dropItemFromBag() {
        //TODO Remove item stack from item list
        //TODO Call drop item function (FloatingItem?)
    }

    //region Getters and Setters

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    //endregion
}
