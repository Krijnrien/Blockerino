package blockerino.resources;

import java.util.ArrayList;
import java.util.List;

public abstract class Resource {
    private int id;
    private String name;

    public Resource(){}

    public Resource(int _id, String _name) {
        id = _id;
        name = _name;
    }

    public int getID(){
        return id;
    }

    public String getName() {
        return name;
    }
}
