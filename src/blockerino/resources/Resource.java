package blockerino.resources;

import java.util.ArrayList;
import java.util.List;

public class Resource {
    protected int id;
    protected String name;

    public Resource(){}

    public Resource(int _id, String _name)
    {
        id = _id;
        name = _name;
    }

    public void load(){}

    public int getID(){
        return id;
    }

    public String getName() {
        return name;
    }
}
