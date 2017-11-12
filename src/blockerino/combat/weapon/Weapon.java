package blockerino.combat.weapon;

import blockerino.combat.bullet.Bullet;
import blockerino.resources.Texture;

public abstract class Weapon {

    private Texture texture;
    private Bullet bullet;
    private float pointingDirection;

    public void render() {
        //TODO render weapon on screen (relative to player?).
    }

    public void input() {
        //TODO Get mouse position relative to player to set pointing direction.
        //TODO Get mouse button click to weapon fire.
        //SHOOT WEAPON HERE
        shoot();
    }

    public void getTexture() {
        //TODO Get weapon texture.
    }

    public void setTexture() {
        //TODO Get weapon texture.
    }

    public void setPointingDirection() {
        //TODO Set weapon pointing direction.
    }

    public void getPointingDirection() {
        //TODO Get weapon pointing direction.
    }

    protected abstract void shoot();
}
