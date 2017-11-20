package blockerino.resources;

import blockerino.util.AABB;
import blockerino.util.Vector2f;

public abstract class Block {

    private Texture texture = null;
    private boolean solid = true;

    private AABB collision;

    public Block() {
        collision = new AABB(new Vector2f(0, 0), 1, 1);
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture _texture) {
        texture = _texture;
    }

    public void setCollision(AABB _scale){ collision = _scale;}

    public AABB getCollision() {
        return collision;
    }

    public void setSolid(boolean _solid) {
        this.solid = _solid;
    }

    public boolean getSolid() {
        return this.solid;
    }
}
