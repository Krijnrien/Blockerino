package blockerino.entity;

import blockerino.graphics.Animation;
import blockerino.graphics.Sprite;
import blockerino.util.AABB;
import blockerino.util.KeyHandler;
import blockerino.util.MouseHandler;
import blockerino.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    private final int UP = 0;
    private final int DOWN = 1;
    private final int RIGHT = 2;
    private final int LEFT = 4;

    protected Animation animation;
    protected Sprite sprite;
    protected Vector2f position;
    protected int size;
    protected int currentAnimation;

    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;
    protected boolean attack;
    protected int attackSpeed;
    protected int attackDuration;

    protected float dx;
    protected float dy;

    protected float maxSpeed;
    protected float acc;
    protected float deacc;

    protected AABB hitBounds;
    protected AABB bounds;

    public Entity(Sprite _sprite, Vector2f _origin, int _size) {
        this.sprite = _sprite;
        position = _origin;
        this.size = _size;

        bounds = new AABB(_origin, _size, _size);
        hitBounds = new AABB(new Vector2f(_origin.x + (_size / 2), _origin.y), _size, _size);

        animation = new Animation();
        setAnimation(RIGHT, _sprite.getSpriteFromSpriteArray(RIGHT), 10);
    }

    public void setSprite(Sprite _sprite) {
        this.sprite = _sprite;
    }

    public void setSize(int _size) {
        size = _size;
    }

    public void setMaxSpeed(float _maxSpeed) {
        maxSpeed = _maxSpeed;
    }

    public void setAcc(float _acc) {
        acc = _acc;
    }

    public void setDeacc(float _deacc) {
        _deacc = _deacc;
    }

    public AABB getBounds(){return bounds;}

    public int getSize() {
        return size;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(int _currentAnimation, BufferedImage[] _frames, int _delay) {
        currentAnimation = _currentAnimation;
        animation.setFrames(_frames);
        animation.setDelay(_delay);
    }

    public void animate() {
        if (up) {
            if (currentAnimation != UP || animation.getDelay() == 1) {
                setAnimation(UP, sprite.getSpriteFromSpriteArray(UP), 5);
            }
        } else if (down) {
            if (currentAnimation != DOWN || animation.getDelay() == 1) {
                setAnimation(DOWN, sprite.getSpriteFromSpriteArray(DOWN), 5);
            }
        } else if (left) {
            if (currentAnimation != LEFT || animation.getDelay() == 1) {
                setAnimation(LEFT, sprite.getSpriteFromSpriteArray(LEFT), 5);
            }
        } else if (right) {
            if (currentAnimation != RIGHT || animation.getDelay() == 1) {
                setAnimation(RIGHT, sprite.getSpriteFromSpriteArray(RIGHT), 5);
            }
        } else {
            setAnimation(currentAnimation, sprite.getSpriteFromSpriteArray(currentAnimation), -1);
        }
    }

    private void setHitBoxDirection() {
        if (up) {
            hitBounds.setYOffset(-size / 2);
            hitBounds.setXOffset(-size / 2);
        } else if (down) {
            hitBounds.setYOffset(size / 2);
            hitBounds.setXOffset(-size / 2);
        } else if (left) {
            hitBounds.setYOffset(-size);
            hitBounds.setXOffset(0);
        } else if (right) {
            hitBounds.setYOffset(0);
            hitBounds.setXOffset(0);
        }

    }

    public void update() {
        animate();
        setHitBoxDirection();
        animation.update();
    }

    public abstract void render(Graphics2D _graphics2D);

    public void input(KeyHandler _key, MouseHandler _mouse) {

    }

}
