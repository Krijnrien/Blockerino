package blockerino.resources.blocks;

import blockerino.resources.Block;
import blockerino.resources.ResourceHandler;

public class BlockStone extends Block {

    public BlockStone() {
        setTexture(ResourceHandler.getLoadedTexture("stone"));
        setSolid(true);
    }
}
