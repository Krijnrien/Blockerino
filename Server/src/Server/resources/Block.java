package Server.resources;

import Server.util.AABB;

public abstract class Block {

    private Texture texture = null;

    private boolean hasCollision;
    private boolean solid = true;

    private AABB collision;

    public Block() {

    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture _texture) {
        texture = _texture;
    }

    public void setCollision(AABB _collision){
        collision = _collision;
        hasCollision = true;
    }

    public AABB getCollision() {
        return collision;
    }

    public void setSolid(boolean _solid) {
        this.solid = _solid;
    }

    public boolean getSolid() {
        return this.solid;
    }

    public boolean hasCollision(){
        return hasCollision;
    }
}
