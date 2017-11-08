package blockerino.resources.blocks;

import blockerino.resources.Block;
import blockerino.resources.ResourceHandler;

public class BlockAir extends Block {

    public BlockAir()
    {
        setTexture(ResourceHandler.getLoadedTexture("air"));
    }
}
