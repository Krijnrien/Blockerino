package blockerino.resources.blocks;

import blockerino.resources.Block;
import blockerino.resources.ResourceHandler;
import blockerino.util.AABB;

public class BlockAir extends Block {

    public BlockAir()
    {
        super();
        setTexture(ResourceHandler.getLoadedTexture("air"));
        setSolid(false);
    }

    @Override
    public void setCollision(AABB _collision){

    }
}
