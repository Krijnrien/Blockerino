package blockerino.entity;

import blockerino.graphics.Animation;
import blockerino.graphics.Sprite;
import blockerino.util.AABB;
import blockerino.util.Vector2f;
import blockerino.world.World;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

//TODO extends world
public abstract class Entity extends WorldObject {

	//region Class variables
	private Sprite sprite;// Texture
	private int size;// Size of entity
	private int weight;//weight of entity
	private AABB hitBounds;// Bounds entity for hits
	private AABB bounds; //bounds entity for collision
	//endregion

	public Entity(){}

	public Entity(Sprite _sprite, Vector2f _origin, int _size) {
		this.sprite = _sprite;
		size = _size;

		bounds = new AABB(_origin, _size, _size);
		hitBounds = new AABB(new Vector2f(_origin.x + (_size / 2), _origin.y), _size, _size);
	}

	//region Abstract methods
	public abstract void update();

	public abstract void render(Graphics2D _graphics2D, AffineTransform _projectionViewMatrix);
	//endregion

	//region Getters and setters
	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public AABB getHitBounds() {
		return hitBounds;
	}

	public void setHitBounds(AABB hitBounds) {
		this.hitBounds = hitBounds;
	}

	public AABB getBounds() {
		return bounds;
	}

	public void setBounds(AABB bounds) {
		this.bounds = bounds;
	}
	//endregion

}