package Server.game.resources;

import Server.game.util.AABB;

public abstract class Block {
    private boolean hasCollision;
    private boolean solid = true;

    private AABB collision;

    public Block() {

    }

    public void setCollision(AABB _collision) {
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

    public boolean hasCollision() {
        return hasCollision;
    }
}
