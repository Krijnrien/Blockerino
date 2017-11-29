package Server.resources;

public class ResourceHandle {

    private int id;
    private String name;

    ResourceHandle(int _id, String _name){
        id = _id;
        name = _name;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }
}
