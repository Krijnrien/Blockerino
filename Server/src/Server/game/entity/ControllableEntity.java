package Server.game.entity;


import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public abstract class ControllableEntity extends Entity {
    //region class variables
    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;
    protected boolean primaryUse;
    protected boolean secondaryUse;

    protected float dx;
    protected float dy;

    protected float maxSpeed;
    protected float acceleration;
    protected float deceleration;
    //endregion


    public void update() {
    }

    //region Abstract methods
    //endregion

    //region Getter & setters
    //endregion
}