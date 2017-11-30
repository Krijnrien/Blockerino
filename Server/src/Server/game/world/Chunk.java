package Server.game.world;

import Server.game.resources.Block;
import Server.game.resources.blocks.BlockAir;
import Server.game.resources.blocks.BlockStone;
import Server.game.util.AABB;
import Server.game.util.Vector2f;
import Server.game.world.generation.Generator;

import java.util.ArrayList;
import java.util.List;

/**
 * Chunk is an allocation and display object for a grid(chunk) of blocks.
 */
public class Chunk {

    private int xPos;
    private int yPos;

    private Block[][] blockData;

    public Chunk(int _chunkSize, int _xPos, int _yPos, Generator _worldGen) {
        xPos = _xPos;
        yPos = _yPos;

        construct(_chunkSize, _worldGen);
    }

    /**
     * Return all block collisions by given AABB
     *
     * @param _collision AABB
     * @return block list
     */
    public boolean getBlockCollisions(AABB _collision) {
        List<Block> collisions = new ArrayList<>();
        for (Block[] aBlockData : blockData) {
            for (Block anABlockData : aBlockData) {
                // If the specific block has collision set

                if (anABlockData.getCollision() != null && anABlockData.getSolid()) {

                    if (_collision.collides(anABlockData.getCollision())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Construct a new chunk with dimensions chunkSize
     *
     * @param _chunkSize chunk size
     * @param _worldGen  world generator
     */
    private void construct(int _chunkSize, Generator _worldGen) {
        blockData = new Block[_chunkSize][_chunkSize];

        generateBlocks(_chunkSize, _worldGen);
    }

    /**
     * Generate blocks with generator
     *
     * @param _chunkSize chunk size
     * @param _worldGen  world generator
     */
    private void generateBlocks(int _chunkSize, Generator _worldGen) {
        for (int i = 0; i < _chunkSize; i++) {
            for (int j = 0; j < _chunkSize; j++) {
                // If the generator returns true on the given position, the position should be stone
                if (_worldGen.getBooleanValue(i + xPos, j + yPos, 0)) {
                    blockData[i][j] = new BlockStone();
                } else {
                    blockData[i][j] = new BlockAir();
                }
                blockData[i][j].setCollision(new AABB(new Vector2f(i + xPos, j + yPos), 1, 1));
            }
        }
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }
}
