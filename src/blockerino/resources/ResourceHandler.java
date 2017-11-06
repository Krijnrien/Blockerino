package blockerino.resources;

import java.util.ArrayList;
import java.util.List;

public class ResourceHandler {

    private static List<Resource> loadedResources = new ArrayList<Resource>();

    public static void addResource(Resource _resource){
        loadedResources.add(_resource);
    }

    /**
     * Return loaded resource id from list by "name" id
     * @param _classType
     * @param _id
     * @return
     */
    public static int getLoadedResourceId(Class _classType, int _id) {
        for (int i = 0; i < loadedResources.size(); i++) {
            if (_classType.isInstance(loadedResources.get(i))) {
                if (loadedResources.get(i).getID() == _id) {
                    return i;
                }
            }
        }

        return 0;
    }

    /**
     * Return resources by id from list, return null if _id is invalid
     * @param _id
     * @return
     */
    public static Resource getResource(int _id)
    {
        if (_id < loadedResources.size()){
            return loadedResources.get(_id);
        }

        return null;
    }
}
