package blockerino.entity.character.equipment;

import blockerino.entity.character.armour.chest.Rare.CobaltChest;
import blockerino.items.Item;

public class Equipment {

	/**
	 * object for each equipment slot
	 */
	private Item helm;
	private Item shoulders;
	private Item chest;
	private Item gloves;
	private Item pants;
	private Item shoes;

	public Equipment() {
		helm = new CobaltChest();
	}


}
