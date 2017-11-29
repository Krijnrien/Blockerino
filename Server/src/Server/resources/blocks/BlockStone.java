package Server.resources.blocks;

import Server.resources.Block;
import Server.resources.ResourceHandler;

public class BlockStone extends Block {

    public BlockStone() {
        super();
        setTexture(ResourceHandler.getLoadedTexture("stone"));
        setSolid(true);
    }
}
