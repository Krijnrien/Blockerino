package blockerino.resources;

public abstract class Block extends Resource{

    private Texture texture = null;

    public Block(int _id, String _name){
        super(_id, _name);
    }

    public Texture getTexture(){
        return texture;
    }

    public void setTexture(Texture _texture){
        texture = _texture;
    }
}
