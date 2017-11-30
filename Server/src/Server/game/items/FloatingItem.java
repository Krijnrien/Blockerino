package Server.game.items;

/**
 * Floating item in the world that has not been picked up.
 */
public class FloatingItem {
	/**
	 * Item that's dropped and now floating in world.
	 */
	private Item item;

	/**
	 * Vertical line originating from floating item with rarity color to accentuate the item its existence to the player.
	 */
	FloatingItem() {
		//setItem(
		//setTexture(item.getSprite?());

	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
