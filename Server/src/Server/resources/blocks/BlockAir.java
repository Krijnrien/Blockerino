package Server.resources.blocks;

import Server.resources.Block;
import Server.resources.ResourceHandler;
import Server.util.AABB;

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
