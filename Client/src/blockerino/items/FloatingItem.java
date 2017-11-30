package blockerino.items;

import blockerino.resources.Texture;

/**
 * Floating item in the world that has not been picked up.
 */
public class FloatingItem {
	/**
	 * Item that's dropped and now floating in world.
	 */
	private Item item;

	/**
	 * Sprite of item to display in world and possible animate
	 */
	private Texture texture;

	/**
	 * Vertical line originating from floating item with rarity color to accentuate the item its existence to the player.
	 */
	private Texture accentuateLine;

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

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Texture getAccentuateLine() {
		return accentuateLine;
	}

	public void setAccentuateLine(Texture accentuateLine) {
		//TODO Get item rarity and create vertical line upwards with rarity color.
		this.accentuateLine = accentuateLine;
	}
}
