package blockerino.world;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

/**
 * The World class represents a world or level which is built out of Chunks
 */
public class World {

    private int chunkSize;
    private List<Chunk> loadedChunks;

    public World(int _chunkSize) {
        chunkSize = _chunkSize;

        loadedChunks = new ArrayList<Chunk>();

        generateDebuggingWorld();
    }

    public void generateDebuggingWorld() {

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j ++){
                loadedChunks.add(new Chunk(chunkSize, i, j));
            }
        }
    }

    /**
     * Render world chunk by chunk
     * @param _graphics2D
     */
    public void render(Graphics2D _graphics2D)
    {
        for(int i = 0; i < loadedChunks.size(); i++)
        {
            loadedChunks.get(i).render(_graphics2D, chunkSize);
        }
    }

    public int getChunkSize() {
        return chunkSize;
    }
}
