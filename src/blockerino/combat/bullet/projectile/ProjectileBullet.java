package blockerino.combat.bullet.projectile;

import blockerino.combat.bullet.Bullet;
import blockerino.resources.Texture;

public class ProjectileBullet extends Bullet{
    @Override
    public int weight() {
        return 0;
    }

    @Override
    public int speed() {
        return 0;
    }

    @Override
    public int resistance() {
        return 0;
    }

    @Override
    public Texture trail() {
        return null;
    }

    @Override
    public Texture collissionEffect() {
        return null;
    }
}
