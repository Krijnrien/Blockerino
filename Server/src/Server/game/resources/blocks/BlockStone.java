package Server.game.resources.blocks;

import Server.game.resources.Block;
import Server.game.resources.ResourceHandler;

public class BlockStone extends Block {

    public BlockStone() {
        super();
        setTexture(ResourceHandler.getLoadedTexture("stone"));
        setSolid(true);
    }
}
