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
import java.awt.image.BufferedImage;

/**
 * Chunk is an allocation and display object for a grid(chunk) of blocks.
 */
public class Chunk {

    private int xPos;
    private int yPos;

    private Sprite sprite = null;
    private Block[][] blockData;

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

        generateBlocks(_chunkSize, _worldGen);
        createSprite(_chunkSize);
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
                if (_worldGen.getBooleanValue(i + xPos, j + yPos, 0)) {
                    blockData[i][j] = new BlockStone();
                }
                else{
                    blockData[i][j] = new BlockAir();
                }
            }
        }
    }

    /**
     * Create a single sprite from the blocks
     * @param _chunkSize chunk size
     */
    private void createSprite(int _chunkSize){
        BufferedImage[][] images = new BufferedImage[_chunkSize][_chunkSize];
        Vector2f[][] positions = new Vector2f[_chunkSize][_chunkSize];

        //Determine new Texture size
        int highestWidth = 0;
        int highestHeight = 0;

        for (int i = 0; i < _chunkSize; i++) {
            for (int j = 0; j < _chunkSize; j++) {

                int blockTextureWidth = blockData[i][j].getTexture().getWidth();
                int blockTextureHeight = blockData[i][j].getTexture().getHeight();

                if (blockTextureWidth > highestWidth){
                    highestWidth = blockTextureWidth;
                }
                if (blockTextureHeight > highestHeight){
                    highestHeight = blockTextureHeight;
                }
            }
        }

        //Create new texture with calculated width and height
        Texture texture = new Texture(new BufferedImage(_chunkSize * highestWidth, _chunkSize * highestHeight, BufferedImage.TYPE_INT_ARGB));

        // Get every block with its position and paint them onto the new texture
        for (int i = 0; i < _chunkSize; i++) {
            for (int j = 0; j < _chunkSize; j++) {

                images[i][j] = blockData[i][j].getTexture().getImageData();
                positions[i][j] = new Vector2f(i * highestWidth, j * highestHeight);
            }

            // Draw row of blocks onto the texture
            texture.drawOnImage(images[i], positions[i]);
        }

        // Create new Sprite with our freshly painted texture
        sprite = new Sprite(texture, new Vector2f(xPos, yPos), new Vector2f(_chunkSize, _chunkSize));
    }

    /**
     * Render the chunk on screen
     */
    public void render(Graphics2D _graphics2) {
        sprite.render(_graphics2);
    }
}
