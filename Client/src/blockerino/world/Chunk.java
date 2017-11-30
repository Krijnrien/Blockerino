package blockerino.world;

import blockerino.graphics.Sprite;
import blockerino.resources.Block;
import blockerino.resources.Texture;
import blockerino.util.AABB;
import blockerino.util.Vector2f;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Chunk is an allocation and display object for a grid(chunk) of blocks.
 */
public class Chunk {

    private int xPos;
    private int yPos;

    private Sprite sprite = null;
    private Block[][] blockData;

    public Chunk(int _chunkSize, int _xPos, int _yPos) {
        xPos = _xPos;
        yPos = _yPos;

        construct(_chunkSize);
    }

    /**
     * Return all block collisions by given AABB
     * @param _collision AABB
     * @return block list
     */
    public List<AABB> getBlockCollisions(AABB _collision){

        List<AABB> collisions = new ArrayList<>();

        for (int i = 0; i < blockData.length; i++){
            for (int j = 0; j < blockData[i].length; j++){
                // If the specific block has collision set

                if (blockData[i][j].getCollision() != null && blockData[i][j].getSolid()) {

                    if (_collision.collides(blockData[i][j].getCollision())) {
                        collisions.add(blockData[i][j].getCollision());
                    }
                }
            }
        }

        return collisions;
    }

    /**
     * Construct a new chunk with dimensions chunkSize
     * @param _chunkSize chunk size
     */
    private void construct(int _chunkSize) {
        blockData = new Block[_chunkSize][_chunkSize];

        createSprite(_chunkSize);
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
        sprite = new Sprite(texture, new Vector2f(xPos + _chunkSize / 2 - 0.5f, yPos + _chunkSize / 2 - 0.5f), new Vector2f(_chunkSize, _chunkSize));
    }

    /**
     * Render the chunk on screen
     */
    public void render(Graphics2D _graphics2, AffineTransform _projectionViewMatrix) {
        sprite.render(_graphics2, _projectionViewMatrix);
    }

    public void renderCollision(Graphics2D _graphics2D, AffineTransform _projectionViewMatrix){
        for (int i = 0; i < blockData.length; i++) {
            for (int j = 0; j < blockData[i].length; j++) {

                if (blockData[i][j].hasCollision()) {
                    blockData[i][j].getCollision().render(_graphics2D, _projectionViewMatrix);
                }
            }
        }
    }

    public int getXPos(){
        return xPos;
    }

    public int getYPos(){
        return yPos;
    }
}
