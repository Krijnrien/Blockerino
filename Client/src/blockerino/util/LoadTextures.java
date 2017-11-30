package blockerino.util;

import blockerino.resources.ResourceHandler;
import blockerino.resources.Texture;
import blockerino.resources.blocks.BlockAir;
import blockerino.resources.blocks.BlockStone;

public class LoadTextures {

	public void loadTextures() {
		ResourceHandler.addTexture(1, "air", new Texture("/blocks/air_temp.png"));
		ResourceHandler.addTexture(2, "stone", new Texture("/blocks/stone_temp.png"));
		ResourceHandler.addTexture(3, "player", new Texture("/entity/linkFormatted.png", 8, 4));
	}

	public void loadBlocks() {
		ResourceHandler.addBlock(1, "air", new BlockAir());
		ResourceHandler.addBlock(2, "stone", new BlockStone());
	}
}
