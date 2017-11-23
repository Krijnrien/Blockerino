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

	private Animation animation;
	private int currentAnimation;

	private boolean up;
	private boolean down;
	private boolean right;
	private boolean left;
	private boolean primaryUse;
	private boolean secondaryUse;

	private float dx;
	private float dy;

	//Standard movement speed
	protected float maxSpeed;
	protected float acceleration;
	protected float deceleration;
	//endregion

	/*public ControllableEntity(Sprite _sprite, Vector2f _origin, Vector2f _size) {
	//	super(_sprite, _origin, _size);
	//	position = _origin;

		//^^^^^ cant do this because getsprite is not set yet, sprite is NULL
	}*/

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
		if(up) {
			if(currentAnimation != sheetRowUp || animation.getDelay() == -1) {
				setAnimation(sheetRowUp, getSprite().getTexture().getPartOfImageDataArray(24, 31), 5);
			}
		} else if(down) {
			if(currentAnimation != sheetRowDown || animation.getDelay() == -1) {
				setAnimation(sheetRowDown, getSprite().getTexture().getPartOfImageDataArray(16, 23), 5);
			}
		} else if(left) {
			if(currentAnimation != sheetRowLeft || animation.getDelay() == -1) {
				setAnimation(sheetRowLeft, getSprite().getTexture().getPartOfImageDataArray(8, 15), 5);
			}
		} else if(right) {
			if(currentAnimation != sheetRowRight || animation.getDelay() == -1) {
				setAnimation(sheetRowRight, getSprite().getTexture().getPartOfImageDataArray(0, 7), 5);
			}
		} else {
			setAnimation(currentAnimation, getSprite().getTexture().getImageData(), -1);
		}
	}

	private void setHitBoxDirection() {
		if(up) {
			position.y -= getMaxSpeed();
			getHitBounds().setYOffset(-getSize().x / 2);
			getHitBounds().setXOffset(-getSize().y / 2);
		} else if(down) {
			position.y += getMaxSpeed();
			getHitBounds().setYOffset(getSize().x / 2);
			getHitBounds().setXOffset(-getSize().y / 2);
		} else if(left) {
			position.x -= getMaxSpeed();
			getHitBounds().setXOffset(-getSize().x);
			getHitBounds().setYOffset(0);
		} else if(right) {
			position.x += getMaxSpeed();
			getHitBounds().setXOffset(0);
			getHitBounds().setYOffset(0);
		}

	}

	//region Abstract methods
	public abstract void render(Graphics2D _graphics2D, AffineTransform _projectionViewMatrix);

	//endregion

	//region Getter & setters
	protected void setAnimation(int _currentAnimation, BufferedImage[] _frames, int _delay) {
		currentAnimation = _currentAnimation;
		animation.setFrames(_frames);
		animation.setDelay(_delay);
	}

	protected void setAnimation(int _currentAnimation, BufferedImage _frame, int _delay) {
		BufferedImage[] frames = new BufferedImage[1];
		frames[0] = _frame;

		currentAnimation = _currentAnimation;
		animation.setFrames(frames);
		animation.setDelay(_delay);
	}

	protected void setSheetRowUp(boolean _up) {
		this.up = _up;
	}

	protected boolean getSheetRowUp() {
		return this.up;
	}

	protected void setDown(boolean _down) {
		this.down = _down;
	}

	protected boolean getDown() {
		return this.down;
	}

	protected boolean getLeft() {
		return this.left;
	}

	protected void setLeft(boolean right) {
		this.left = right;
	}

	protected boolean getRight() {
		return this.right;
	}

	protected void setRight(boolean right) {
		this.right = right;
	}

	protected boolean getPrimaryUse() {
		return this.primaryUse;
	}

	protected void setPrimaryUse(boolean _primaryUse) {
		this.primaryUse = _primaryUse;
	}

	protected boolean getSecondaryUse() {
		return this.secondaryUse;
	}

	protected void setSecondaryUse(boolean _secondaryUse) {
		this.secondaryUse = _secondaryUse;
	}

	protected float getDx() {
		return dx;
	}

	protected void setDx(float dx) {
		this.dx = dx;
	}

	protected float getDy() {
		return dy;
	}

	protected void setDy(float dy) {
		this.dy = dy;
	}

	public void setMaxSpeed(float _maxSpeed) {
		maxSpeed = _maxSpeed;
	}

	public float getMaxSpeed() {
		return this.maxSpeed;
	}

	public float getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(float acceleration) {
		this.acceleration = acceleration;
	}

	public float getDeceleration() {
		return deceleration;
	}

	public void setDeceleration(float deceleration) {
		this.deceleration = deceleration;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	protected Animation getAnimation() {
		return animation;
	}
	//endregion
}