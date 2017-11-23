package blockerino.entity;

import blockerino.graphics.Animation;

import javax.xml.bind.annotation.XmlTransient;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

@XmlTransient
public abstract class ControllableEntity extends Entity {
    //region class variables
    private final int sheetRowUp = 3;
    private final int sheetRowDown = 2;
    private final int sheetRowRight = 0;
    private final int sheetRowLeft = 1;

    protected Animation animation;
    private int currentAnimation;

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

    public void attachAnimation() {
        animation = new Animation();
        setAnimation(sheetRowRight, getSprite().getTexture().getPartOfImageDataArray(0, 7), 10);
    }

    public void update() {
        animate();
        setHitBoxDirection();
        animation.update();
    }

    private void animate() {
        if (up) {
            if (currentAnimation != sheetRowUp || animation.getDelay() == -1) {
                setAnimation(sheetRowUp, getSprite().getTexture().getPartOfImageDataArray(24, 31), 5);
            }
        } else if (down) {
            if (currentAnimation != sheetRowDown || animation.getDelay() == -1) {
                setAnimation(sheetRowDown, getSprite().getTexture().getPartOfImageDataArray(16, 23), 5);
            }
        } else if (left) {
            if (currentAnimation != sheetRowLeft || animation.getDelay() == -1) {
                setAnimation(sheetRowLeft, getSprite().getTexture().getPartOfImageDataArray(8, 15), 5);
            }
        } else if (right) {
            if (currentAnimation != sheetRowRight || animation.getDelay() == -1) {
                setAnimation(sheetRowRight, getSprite().getTexture().getPartOfImageDataArray(0, 7), 5);
            }
        } else {
            setAnimation(currentAnimation, getSprite().getTexture().getImageData(), -1);
        }
    }

    private void setHitBoxDirection() {
        if (up) {
            position.y -= maxSpeed;
            getHitBounds().setYOffset(-getSize().x / 2);
            getHitBounds().setXOffset(-getSize().y / 2);
        } else if (down) {
            position.y += maxSpeed;
            getHitBounds().setYOffset(getSize().x / 2);
            getHitBounds().setXOffset(-getSize().y / 2);
        } else if (left) {
            position.x -= maxSpeed;
            getHitBounds().setXOffset(-getSize().x);
            getHitBounds().setYOffset(0);
        } else if (right) {
            position.x += maxSpeed;
            getHitBounds().setXOffset(0);
            getHitBounds().setYOffset(0);
        }

    }

    //region Abstract methods
    public abstract void render(Graphics2D _graphics2D, AffineTransform _projectionViewMatrix);
    //endregion

    //region Getter & setters
    private void setAnimation(int _currentAnimation, BufferedImage[] _frames, int _delay) {
        currentAnimation = _currentAnimation;
        animation.setFrames(_frames);
        animation.setDelay(_delay);
    }

    private void setAnimation(int _currentAnimation, BufferedImage _frame, int _delay) {
        BufferedImage[] frames = new BufferedImage[1];
        frames[0] = _frame;

        currentAnimation = _currentAnimation;
        animation.setFrames(frames);
        animation.setDelay(_delay);
    }
    //endregion
}