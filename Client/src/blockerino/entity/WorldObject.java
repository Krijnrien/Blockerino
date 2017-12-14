package blockerino.entity;


import blockerino.util.Vector2f;

import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public abstract class WorldObject {

    protected Vector2f position;
    protected Vector2f scale;

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public Vector2f getScale() {
        return scale;
    }

    public void setScale(Vector2f scale) {
        this.scale = scale;
    }
}
