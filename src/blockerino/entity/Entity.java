package blockerino.entity;

import blockerino.graphics.Sprite;
import blockerino.util.AABB;
import blockerino.util.Vector2f;

import javax.xml.bind.annotation.XmlTransient;
import java.awt.*;
import java.awt.geom.AffineTransform;

//TODO extends world

@XmlTransient
public abstract class Entity extends WorldObject {

	//region Class variables
	private Sprite sprite;// Texture
	private int weight;//weight of entity
	private Vector2f size;
	private AABB hitBounds;// Bounds entity for hits
	private AABB bounds; //bounds entity for collision

	private AABB leftCollision;
	private AABB rightCollision;
	private AABB topCollision;
	private AABB bottomCollision;
	//endregion

	//public Entity(){}

	/*public Entity(Sprite _sprite, Vector2f _origin, Vector2f _scale) {
		position = _origin;
		this.sprite = _sprite;
		scale = _scale;


		}*/

	public void setBothBounds() {
		Vector2f _origin = position;
		Vector2f _scale = getScale();

		bounds = new AABB(_origin, _scale.x, _scale.y);
 		hitBounds = new AABB(new Vector2f(_origin.x + (scale.x / 2), _origin.y), _scale.x, _scale.y);

	}

	public void setCollissions() {
		Vector2f _origin = position;
		Vector2f _scale = getScale();
		float collWidth = scale.x / 4;
		float collHeight = scale.y / 4;

		leftCollision = new AABB(new Vector2f(_origin.x - (scale.x / 2) + (collWidth / 2), _origin.y), collWidth, _scale.y - 0.5f);
		rightCollision = new AABB(new Vector2f(_origin.x + (scale.x / 2) - (collWidth / 2), _origin.y), collWidth, _scale.y - 0.5f);
		topCollision = new AABB(new Vector2f(_origin.x, _origin.y - (scale.y / 2) + (collHeight / 2)), scale.x - 0.5f, collHeight);
		bottomCollision = new AABB(new Vector2f(_origin.x, _origin.y + (scale.y / 2) - (collHeight / 2)), scale.x - 0.5f, collHeight);

	}

	public void renderCollision(Graphics2D _graphics2D, AffineTransform _projectionViewMatrix) {
		leftCollision.render(_graphics2D, _projectionViewMatrix, position);
		rightCollision.render(_graphics2D, _projectionViewMatrix, position);
		topCollision.render(_graphics2D, _projectionViewMatrix, position);
		bottomCollision.render(_graphics2D, _projectionViewMatrix, position);
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

	public Vector2f getSize() {
		return size;
	}

	public void setSize(Vector2f size) {
		this.size = size;
	}
	//endregion

}
