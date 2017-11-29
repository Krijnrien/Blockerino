package Server.resources;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ResourceHandler {

    private static Map<ResourceHandle, Texture> loadedTextures = new HashMap<>();
    private static Map<ResourceHandle, Block> loadedBlocks = new HashMap<>();

    public static void addTexture(int _id, String _name, Texture _texture){
        loadedTextures.put(new ResourceHandle(_id, _name), _texture);
    }

    public static void addBlock(int _id, String _name, Block _block){
        loadedBlocks.put(new ResourceHandle(_id, _name), _block);
    }

    /**
     * Return loaded resource from list by "name" id
     * @param _id texture Id
     * @return Texture if found, null if not
     */
    public static Texture getLoadedTexture(int _id) {
        for ( ResourceHandle rhandle : loadedTextures.keySet() ) {

            if (rhandle.getId() == _id) {
                return loadedTextures.get(rhandle);
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
        for ( ResourceHandle rhandle : loadedTextures.keySet() ) {
            if (Objects.equals(rhandle.getName(), _name)) {
                return loadedTextures.get(rhandle);
            }
        }

        return null;
    }

    public static Block getLoadedBlock(int _id) {
        for ( ResourceHandle rhandle : loadedBlocks.keySet() ) {

            if (rhandle.getId() == _id) {
                return loadedBlocks.get(rhandle);
            }
        }

        return null;
    }

    public static Block getLoadedBlock(String _name) {
        for ( ResourceHandle rhandle : loadedBlocks.keySet() ) {

            if (Objects.equals(rhandle.getName(), _name)) {
                return loadedBlocks.get(rhandle);
            }
        }

        return null;
    }
}
