package blockerino.world;

import blockerino.graphics.Sprite;
import blockerino.resources.Block;
import blockerino.resources.ResourceHandler;
import blockerino.resources.Texture;
import blockerino.resources.blocks.BlockAir;
import blockerino.util.Vector2f;

import java.awt.*;

public class Chunk {

    private int xPos;
    private int yPos;

    private Sprite[][] sprites;
    private Block[][] blockData;

    private final int BLOCK_SIZE = 16;

    public Chunk(int chunkSize, int _xPos, int _yPos) {
        xPos = _xPos;
        yPos = _yPos;

        construct(chunkSize);
    }

    /**
     * Fill the blockdata with blocks, id = 0
     * @param chunkSize
     */
    private void construct(int chunkSize) {
        blockData = new Block[chunkSize][chunkSize];
        sprites = new Sprite[chunkSize][chunkSize];

        for (int i = 0; i < chunkSize; i++) {
            for (int j = 0; j < chunkSize; j++) {
                //TODO read from file or generate from worldgen

                blockData[i][j] = new BlockAir();

                Texture texture = blockData[i][j].getTexture();
                sprites[i][j] = new Sprite(texture,
                                            new Vector2f((i * BLOCK_SIZE) + (xPos * BLOCK_SIZE * chunkSize), (j * BLOCK_SIZE) + (yPos * BLOCK_SIZE * chunkSize)),
                                            new Vector2f(BLOCK_SIZE, BLOCK_SIZE));
            }
        }
    }

    /**
     * TODO When the rendering is based on a viewport, remove the " .. * TILE_SIZE" and setting the image size to 1
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
