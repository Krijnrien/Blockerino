package blockerino.world;

import blockerino.entity.Entity;
import blockerino.util.Vector2f;
import blockerino.world.generation.Generator;
import javafx.scene.Camera;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.ArrayList;

/**
 * The World class represents a world or level which is built out of Chunks
 */
public class World {

    private int chunkSize;
    private List<Chunk> loadedChunks;
    private Generator worldGen;
    private int chunkLoadRadius = 3;
    private int maxChunkLoaded = 40; //This number can't be lower than squared(ChunkLoadRadius + ChunkLoadRadius)


    public World(int _chunkSize, Generator _generator) {
        chunkSize = _chunkSize;
        worldGen = _generator;

        loadedChunks = new ArrayList<>();
    }

    // Get the chunk in which the given coords are in.
    public Chunk getChunk(int _xPos, int _yPos){
        for(int i = 0; i < loadedChunks.size(); i++){
            if (_xPos >= loadedChunks.get(i).getXPos() &&
                    _xPos < loadedChunks.get(i).getXPos() + chunkSize &&
                    _yPos >= loadedChunks.get(i).getYPos()  &&
                    _yPos < loadedChunks.get(i).getYPos() + chunkSize){
                return loadedChunks.get(i);
            }
        }

        return null;
    }

    // Get the chunk in which the given coords are in.
    public Chunk getChunk(Vector2f _pos){

        int xPos = Math.round(_pos.x);
        int yPos = Math.round(_pos.y);

        for(int i = 0; i < loadedChunks.size(); i++){
            if (xPos >= loadedChunks.get(i).getXPos() &&
                    xPos < loadedChunks.get(i).getXPos() + chunkSize &&
                    yPos >= loadedChunks.get(i).getYPos()  &&
                    yPos < loadedChunks.get(i).getYPos() + chunkSize){
                return loadedChunks.get(i);
            }
        }

        return null;
    }

    /**
     * Generate (missing) chunks with radius of chunkLoadRadius
     * @param _pos target position
     */
    public void generateChunksRadius(Vector2f _pos){

        int xPos = (int)(_pos.x - (_pos.x % chunkSize)); //Get chunk position from _pos;
        int yPos = (int)(_pos.y - (_pos.y % chunkSize));

        for (int x = xPos - chunkLoadRadius * chunkSize; x < xPos + chunkLoadRadius * chunkSize; x += chunkSize){
            for (int y = yPos - chunkLoadRadius * chunkSize; y < yPos + chunkLoadRadius * chunkSize; y += chunkSize){
                Chunk c = getChunk(x, y);

                if (c == null){
                    c = new Chunk(chunkSize, x, y, worldGen);
                    loadedChunks.add(c);
                }

                if (loadedChunks.size() > maxChunkLoaded){
                    removeChunkFurthestAway(_pos);
                }
            }
        }
    }

    /**
     * Remove chunk furthest away from the given position
     * Length is determined by Pythagoras function
     * @param _pos target position
     */
    public void removeChunkFurthestAway(Vector2f _pos){

        double maxLength = 0;
        int furthestChunkId = 0;

        for (int i = 0; i < loadedChunks.size(); i++){
            double width = loadedChunks.get(i).getXPos() - _pos.x;
            double height = loadedChunks.get(i).getYPos() - _pos.y;

            double length = Math.sqrt((width * width) + (height * height));

            if (length > maxLength){
                maxLength = length;
                furthestChunkId = i;
            }
        }

        loadedChunks.remove(furthestChunkId);
    }

    /**
     * Render world chunk by chunk
     * @param _graphics2D graphics2D Object
     */
    public void render(Graphics2D _graphics2D, AffineTransform _projectionViewMatrix)
    {
        for(int i = 0; i < loadedChunks.size(); i++)
        {
            loadedChunks.get(i).render(_graphics2D, _projectionViewMatrix);
        }
    }

    public int getChunkSize() {
        return chunkSize;
    }
}
