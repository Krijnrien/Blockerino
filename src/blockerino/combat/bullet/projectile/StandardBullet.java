package blockerino.combat.bullet.projectile;

import blockerino.resources.Texture;
import blockerino.util.Vector2f;

public class StandardBullet extends ProjectileBullet {

    private Vector2f position;
    private int weight;
    private int speed;
    private int resistance;
    private Texture tail;
    private Texture colissionEffect;

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    @Override
    public Texture getTail() {
        return tail;
    }

    public void setTail(Texture tail) {
        this.tail = tail;
    }

    public Texture getColissionEffect() {
        return colissionEffect;
    }

    public void setColissionEffect(Texture colissionEffect) {
        this.colissionEffect = colissionEffect;
    }
}
