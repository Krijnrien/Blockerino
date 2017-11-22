package blockerino.entity.character.combat.bullet.projectile;

import blockerino.entity.character.combat.bullet.Bullet;
import blockerino.resources.Texture;

public abstract class ProjectileBullet extends Bullet {


    public Texture texture() {
        return null; //TODO temporary null to avoid compile error
    }

    public abstract int getWeight();

    public abstract int getSpeed();

    public abstract int getResistance(); // Amount of resistance to traveling bullet resulting in deceleration.

    public abstract Texture getTail();

    public abstract Texture getColissionEffect();
}
