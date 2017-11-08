package blockerino.entity;

import blockerino.graphics.Animation;
import blockerino.graphics.Sprite;
import blockerino.util.AABB;
import blockerino.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

	private final int UP = 3;
	private final int DOWN = 2;
	private final int RIGHT = 0;
	private final int LEFT = 1;

	Animation animation;
	private Sprite sprite;
	Vector2f position;
	int size;
	private int currentAnimation;

	//TODO Getter & setters.
	boolean up;
	boolean down;
	boolean right;
	boolean left;
	boolean attack;

	float dx;
	float dy;
	boolean airborne;
	float weight;

	float maxSpeed = 3f;
	float acc = 2f;
	float deacc = 0.3f;

	private AABB hitBounds;
	private AABB bounds;

	Entity(Sprite _sprite, Vector2f _origin, int _size) {
		this.sprite = _sprite;
		position = _origin;
		this.size = _size;

		bounds = new AABB(_origin, _size, _size);
		hitBounds = new AABB(new Vector2f(_origin.x + (_size / 2), _origin.y), _size, _size);

		animation = new Animation();
		setAnimation(RIGHT, sprite.getTexture().getPartOfImageDataArray(0, 7), 10);
	}

	/**
	 * Gravity affects weight. Weight affects entities closer to the planets core, heavier equals a larger gravity pull force.
	 */
	public void setWeight() {
	}

	public void getWeight() {
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

	public AABB getBounds() {
		return bounds;
	}

	public int getSize() {
		return size;
	}

	public Animation getAnimation() {
		return animation;
	}

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

	private void animate() {
		if(up) {
			if(currentAnimation != UP || animation.getDelay() == -1) {
				setAnimation(UP, sprite.getTexture().getPartOfImageDataArray(24, 31), 5);
			}
		} else if(down) {
			if(currentAnimation != DOWN || animation.getDelay() == -1) {
				setAnimation(DOWN, sprite.getTexture().getPartOfImageDataArray(16, 23), 5);
			}
		} else if(left) {
			if(currentAnimation != LEFT || animation.getDelay() == -1) {
				setAnimation(LEFT, sprite.getTexture().getPartOfImageDataArray(8, 15), 5);
			}
		} else if(right) {
			if(currentAnimation != RIGHT || animation.getDelay() == -1) {
				setAnimation(RIGHT, sprite.getTexture().getPartOfImageDataArray(0, 7), 5);
			}
		} else {
			setAnimation(currentAnimation, sprite.getTexture().getImageData(), -1);
		}
	}

	private void setHitBoxDirection() {
		if(up) {
			hitBounds.setYOffset(-size / 2);
			hitBounds.setXOffset(-size / 2);
		} else if(down) {
			hitBounds.setYOffset(size / 2);
			hitBounds.setXOffset(-size / 2);
		} else if(left) {
			hitBounds.setXOffset(-size);
			hitBounds.setYOffset(0);
		} else if(right) {
			hitBounds.setXOffset(0);
			hitBounds.setYOffset(0);
		}

	}

	public void update() {
		animate();
		setHitBoxDirection();
		animation.update();
	}

	public abstract void render(Graphics2D _graphics2D);

}