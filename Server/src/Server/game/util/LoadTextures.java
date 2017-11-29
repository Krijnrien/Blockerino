package Server.game.util;

import Server.game.resources.ResourceHandler;
import Server.game.resources.Texture;
import Server.game.resources.blocks.BlockAir;
import Server.game.resources.blocks.BlockStone;

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
