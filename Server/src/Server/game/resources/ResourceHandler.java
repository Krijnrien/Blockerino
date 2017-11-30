package Server.game.resources;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ResourceHandler {

    private static Map<ResourceHandle, Block> loadedBlocks = new HashMap<>();


    public static void addBlock(int _id, String _name, Block _block){
        loadedBlocks.put(new ResourceHandle(_id, _name), _block);
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
