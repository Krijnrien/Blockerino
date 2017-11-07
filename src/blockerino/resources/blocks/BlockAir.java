package blockerino.resources.blocks;

import blockerino.resources.Block;
import blockerino.resources.ResourceHandler;

public class BlockAir extends Block {

    public BlockAir(int _id, String _name)
    {
        super(_id, _name);

        setTexture(ResourceHandler.getLoadedTexture("air"));
    }
}
