package blockerino.resources;

import java.util.ArrayList;
import java.util.List;

public class ResourceHandler {

    private static List<Texture> loadedTextures = new ArrayList<>();

    public static void addTexture(Texture _texture){
        loadedTextures.add(_texture);
    }

    /**
     * Return loaded resource id from list by "name" id
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
}
