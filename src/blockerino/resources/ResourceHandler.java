package blockerino.resources;

import java.util.ArrayList;
import java.util.List;

public class ResourceHandler {

    private static List<Texture> loadedTextures = new ArrayList<>();
    private static List<Block> loadedBlocks = new ArrayList<>();

    public static void addTexture(Texture _texture){
        loadedTextures.add(_texture);
    }

    public static void addBlock(Block _block){
        loadedBlocks.add(_block);
    }

    /**
     * Return loaded resource from list by "name" id
     * @param _id texture Id
     * @return Texture if found, null if not
     */
    public static Texture getLoadedTexture(int _id) {
        for (int i = 0; i < loadedTextures.size(); i++) {
            if (loadedTextures.get(i).getID() == _id) {
                return loadedTextures.get(i);
            }
        }

        return null;
    }

    /**
     * Return loaded resource from list by name
     * @param _name texture Id
     * @return Texture if found, null if not
     */
    public static Texture getLoadedTexture(String _name) {
        for (int i = 0; i < loadedTextures.size(); i++) {
            if (loadedTextures.get(i).getName() == _name) {
                return loadedTextures.get(i);
            }
        }

        return null;
    }

    public static Block getLoadedBlock(int _id) {
        for (int i = 0; i < loadedBlocks.size(); i++) {
            if (loadedBlocks.get(i).getID() == _id) {
                return loadedBlocks.get(i);
            }
        }

        return null;
    }

    public static Block getLoadedBlock(String _name) {
        for (int i = 0; i < loadedBlocks.size(); i++) {
            if (loadedBlocks.get(i).getName() == _name) {
                return loadedBlocks.get(i);
            }
        }

        return null;
    }
}
