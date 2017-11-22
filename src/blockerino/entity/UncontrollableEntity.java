package blockerino.entity;

import blockerino.graphics.Sprite;
import blockerino.util.Vector2f;

import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class UncontrollableEntity extends Entity {
	//region class variables
	protected Vector2f position;
	private float dx;
	private float dy;
	//endregion

	public UncontrollableEntity(Sprite _sprite, Vector2f _origin, int _size) {
		super(_sprite, _origin, _size);
		position = _origin;
	}

	public void update() {
		//TODO
	}

	public Vector2f getPosition() {
		return position;
	}

	//region Abstract methods
	public abstract void render(Graphics2D _graphics2D, AffineTransform _projectionViewMatrix);

	public abstract void setWeight(float _weight);

	public abstract void setSize(float _size);
	//endregion

	//region Getter & setters
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
	//endregion
}