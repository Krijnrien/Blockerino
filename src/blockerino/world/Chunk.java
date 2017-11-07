package blockerino.world;

import blockerino.graphics.Sprite;
import blockerino.resources.ResourceHandler;
import blockerino.resources.Texture;
import blockerino.util.Vector2f;

import java.awt.*;

public class Chunk {

    private int xPos;
    private int yPos;

    private final int TILE_SIZE = 32; //TODO should'nt be necessary when the rendering is based on a viewport

    private Block[][] blockData;

    public Chunk(int chunkSize, int _xPos, int _yPos) {
        xPos = _xPos;
        yPos = _yPos;

        construct(chunkSize);
    }

    /**
     * Fill the blockdata with blocks, id = 0
     * @param chunkSize size of chunk
     */
    private void construct(int chunkSize) {
        blockData = new Block[chunkSize][chunkSize];

        for (int i = 0; i < chunkSize; i++) {
            for (int j = 0; j < chunkSize; j++) {
                //TODO read from file or generate from worldgen

                blockData[i][j] = new Block(0);
            }
        }
    }

    /**
     * TODO When the rendering is based on a viewport, remove the " .. * TILE_SIZE" and setting the image size to 1
     * Render the chunk on screen
     * @param chunkSize size of chunk
     */
    public void render(Graphics2D _graphics2, int chunkSize) {
        for (int i = 0; i < chunkSize; i++) {
            for (int j = 0; j < chunkSize; j++) {

                Texture texture = ResourceHandler.getLoadedTexture(1);
                Sprite sprite = new Sprite(texture,
                                            new Vector2f(i * TILE_SIZE + xPos, j * TILE_SIZE + yPos),
                                            new Vector2f(TILE_SIZE, TILE_SIZE));

                sprite.render(_graphics2);
            }
        }
    }
}
