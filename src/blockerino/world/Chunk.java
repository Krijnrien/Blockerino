package blockerino.world;

import blockerino.graphics.Sprite;
import blockerino.resources.Block;
import blockerino.resources.ResourceHandler;
import blockerino.resources.Texture;
import blockerino.resources.blocks.BlockAir;
import blockerino.resources.blocks.BlockStone;
import blockerino.util.Vector2f;
import blockerino.world.generation.Generator;

import java.awt.*;

/**
 * TODO Combine sprite array into a single sprite, reducing render calls. Update the sprite when chunk is being changed.
 */
public class Chunk {

    private int xPos;
    private int yPos;

    private Sprite[][] sprites;
    private Block[][] blockData;

    private final int BLOCK_SIZE = 16;

    public Chunk(int _chunkSize, int _xPos, int _yPos, Generator _worldGen) {
        xPos = _xPos;
        yPos = _yPos;

        construct(_chunkSize, _worldGen);
    }

    /**
     * Construct a new chunk with dimensions chunkSize
     * @param _chunkSize chunk size
     * @param _worldGen world generator
     */
    private void construct(int _chunkSize, Generator _worldGen) {
        blockData = new Block[_chunkSize][_chunkSize];
        sprites = new Sprite[_chunkSize][_chunkSize];

        generateBlocks(_chunkSize, _worldGen);
    }

    /**
     * Generate blocks with generator
     * @param _chunkSize chunk size
     * @param _worldGen world generator
     */
    private void generateBlocks(int _chunkSize, Generator _worldGen){
        for (int i = 0; i < _chunkSize; i++) {
            for (int j = 0; j < _chunkSize; j++) {

                // If the generator returns true on the given position, the position should be stone
                if (_worldGen.getBooleanValue(i + xPos, j + yPos)) {
                    blockData[i][j] = new BlockStone();
                }
                else{
                    blockData[i][j] = new BlockAir();
                }

                // Create sprite of the block
                Texture texture = blockData[i][j].getTexture();
                sprites[i][j] = new Sprite(texture,
                        new Vector2f((i * BLOCK_SIZE) + (xPos * BLOCK_SIZE), (j * BLOCK_SIZE) + (yPos * BLOCK_SIZE)),
                        new Vector2f(BLOCK_SIZE, BLOCK_SIZE));
            }
        }
    }

    /**
     * Render the chunk on screen
     * @param chunkSize
     */
    public void render(Graphics2D _graphics2, int chunkSize) {
        for (int i = 0; i < chunkSize; i++) {
            for (int j = 0; j < chunkSize; j++) {

                sprites[i][j].render(_graphics2);
            }
        }
    }
}
