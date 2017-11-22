package blockerino.entity;

import blockerino.util.Vector2f;

public abstract class WorldObject {
    //TODO just placeholder class

    private Vector2f position;

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }
}
