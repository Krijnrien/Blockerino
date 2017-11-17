package blockerino.entity;

import blockerino.graphics.Animation;
import blockerino.graphics.Sprite;
import blockerino.util.AABB;
import blockerino.util.Vector2f;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class Entity {

    //region class variables
    protected Sprite sprite;

    //endregion

    private final int sheetRowUp = 3;
    private final int sheetRowDown = 2;
    private final int sheetRowRight = 0;
    private final int sheetRowLeft = 1;

    private Animation animation;
    protected Vector2f position;
    private int currentAnimation;

    private boolean up;
    private boolean down;
    private boolean right;
    private boolean left;
    private boolean primaryUse;
    private boolean secondaryUse;

    private float dx;
    private float dy;

    private float flSizeTemp; // Declaring sizeTamp as it's used multiple times across different methods.
    private AABB hitBounds;
    private AABB bounds;

    Entity(Sprite _sprite, Vector2f _origin, int _size) {
        this.sprite = _sprite;
        position = _origin;
        flSizeTemp = getSize();

        bounds = new AABB(_origin, _size, _size);
        hitBounds = new AABB(new Vector2f(_origin.x + (_size / 2), _origin.y), _size, _size);

        animation = new Animation();
        setAnimation(sheetRowRight, sprite.getTexture().getPartOfImageDataArray(0, 7), 10);
    }

    public abstract void render(Graphics2D _graphics2D, AffineTransform _projectionViewMatrix);

    public void update() {
        animate();
        setHitBoxDirection();
        animation.update();
    }

    private void animate() {
        if (up) {
            if (currentAnimation != sheetRowUp || animation.getDelay() == -1) {
                setAnimation(sheetRowUp, sprite.getTexture().getPartOfImageDataArray(24, 31), 5);
            }
        } else if (down) {
            if (currentAnimation != sheetRowDown || animation.getDelay() == -1) {
                setAnimation(sheetRowDown, sprite.getTexture().getPartOfImageDataArray(16, 23), 5);
            }
        } else if (left) {
            if (currentAnimation != sheetRowLeft || animation.getDelay() == -1) {
                setAnimation(sheetRowLeft, sprite.getTexture().getPartOfImageDataArray(8, 15), 5);
            }
        } else if (right) {
            if (currentAnimation != sheetRowRight || animation.getDelay() == -1) {
                setAnimation(sheetRowRight, sprite.getTexture().getPartOfImageDataArray(0, 7), 5);
            }
        } else {
            setAnimation(currentAnimation, sprite.getTexture().getImageData(), -1);
        }
    }

    private void setHitBoxDirection() {
        if (up) {
            hitBounds.setYOffset(-flSizeTemp / 2);
            hitBounds.setXOffset(-flSizeTemp / 2);
        } else if (down) {
            hitBounds.setYOffset(flSizeTemp / 2);
            hitBounds.setXOffset(-flSizeTemp / 2);
        } else if (left) {
            hitBounds.setXOffset(-flSizeTemp);
            hitBounds.setYOffset(0);
        } else if (right) {
            hitBounds.setXOffset(0);
            hitBounds.setYOffset(0);
        }

    }

    //region Getter & setters

    void setAnimation(int _currentAnimation, BufferedImage[] _frames, int _delay) {
        currentAnimation = _currentAnimation;
        animation.setFrames(_frames);
        animation.setDelay(_delay);
    }

    void setAnimation(int _currentAnimation, BufferedImage _frame, int _delay) {
        BufferedImage[] frames = new BufferedImage[1];
        frames[0] = _frame;

        currentAnimation = _currentAnimation;
        animation.setFrames(frames);
        animation.setDelay(_delay);
    }

    Animation getAnimation() {
        return animation;
    }

    void setSheetRowUp(boolean _up) {
        this.up = _up;
    }

    boolean getSheetRowUp() {
        return this.up;
    }

    void setDown(boolean _down) {
        this.down = _down;
    }

    boolean getDown() {
        return this.down;
    }

    boolean getLeft() {
        return this.left;
    }

    void setLeft(boolean right) {
        this.left = right;
    }

    boolean getRight() {
        return this.right;
    }

    void setRight(boolean right) {
        this.right = right;
    }

    boolean getPrimaryUse() {
        return this.primaryUse;
    }

    void setPrimaryUse(boolean _primaryUse) {
        this.primaryUse = _primaryUse;
    }

    boolean getSecondaryUse() {
        return this.secondaryUse;
    }

    void setSecondaryUse(boolean _secondaryUse) {
        this.secondaryUse = _secondaryUse;
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    void setBounds(AABB _bounds) {
        this.bounds = _bounds;
    }

    AABB getBounds() {
        return bounds;
    }


    //endregion

    //region Abstract methods
    public void setSprite(Sprite _sprite) {
        this.sprite = _sprite;
    }

    public abstract void setWeight(float _weight);

    public abstract float getWeight();

    public abstract void setMaxSpeed(float _maxSpeed);

    public abstract float getMaxSpeed();

    public abstract void setAcceleration(float _acceleration);

    public abstract float getAcceleration();

    public abstract void setDeceleration(float _deceleration);

    public abstract float getDeceleration();

    public abstract float getSize();

    public abstract void setSize(float _size);

    //endregion

}