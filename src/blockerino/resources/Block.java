package blockerino.resources;

public abstract class Block {

    private Texture texture = null;
    private boolean solid;

    public Block() {
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture _texture) {
        texture = _texture;
    }


    public void setSolid(boolean _solid) {
        this.solid = _solid;
    }

    public boolean getSolid() {
        return this.solid;
    }
}
