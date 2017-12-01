package Server.game.resources.blocks;

import Server.game.resources.Block;
import Server.game.resources.ResourceHandler;
import Server.game.util.AABB;

public class BlockAir extends Block {

    public BlockAir()
    {
        super();
        setSolid(false);
    }

    @Override
    public void setCollision(AABB _collision){

    }
}
