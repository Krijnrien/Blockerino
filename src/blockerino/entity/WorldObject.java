package blockerino.entity;

import blockerino.util.Vector2f;

import javax.xml.bind.annotation.XmlTransient;


@XmlTransient
public abstract class WorldObject {
    //TODO just placeholder class

    protected Vector2f position;

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }
}
