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

    public abstract void load(String _file);

    public int getID(){
        return id;
    }

    public String getName() {
        return name;
    }
}
