package Server.world;

import Server.util.Vector2f;
import Server.world.generation.Generator;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

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
        for(Chunk loadedChunk : loadedChunks) {
            if(_xPos >= loadedChunk.getXPos() &&
                    _xPos < loadedChunk.getXPos() + chunkSize &&
                    _yPos >= loadedChunk.getYPos() &&
                    _yPos < loadedChunk.getYPos() + chunkSize) {
                return loadedChunk;
            }
        }

        return null;
    }

    // Get the chunk in which the given coords are in.
    public Chunk getChunk(Vector2f _pos){

        int xPos = Math.round(_pos.x);
        int yPos = Math.round(_pos.y);

        for(Chunk loadedChunk : loadedChunks) {
            if(xPos >= loadedChunk.getXPos() &&
                    xPos < loadedChunk.getXPos() + chunkSize &&
                    yPos >= loadedChunk.getYPos() &&
                    yPos < loadedChunk.getYPos() + chunkSize) {
                return loadedChunk;
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
    private void removeChunkFurthestAway(Vector2f _pos){

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
     * First doodle for a method that places a block on given coords, not finished
     * @param _xPos
     * @param _yPos
     */
    public void setBlock(int _xPos, int _yPos){
        Chunk chunk = getChunk(_xPos, _yPos);
        int xPosInChunk = _xPos % chunkSize;
        int yPosInChunk = _yPos % chunkSize;

        if (chunk != null){

        }
    }

    public int getChunkSize() {
        return chunkSize;
    }
}
