package blockerino.resources;

public abstract class Block{

    private Texture texture = null;

    public Block(){ }

    public Texture getTexture(){
        return texture;
    }

    public void setTexture(Texture _texture){
        texture = _texture;
    }
}
