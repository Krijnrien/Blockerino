package blockerino.entity.character;

import blockerino.entity.ControllableEntity;
import blockerino.entity.Entity;
import blockerino.entity.character.armour.Armour;
import blockerino.entity.character.armour.chest.Rare.CobaltChest;
import blockerino.entity.character.combat.bullet.Bullet;
import blockerino.entity.character.combat.bullet.projectile.StandardBullet;
import blockerino.graphics.Sprite;
import blockerino.resources.ResourceHandler;
import blockerino.resources.Texture;
import blockerino.util.KeyHandler;
import blockerino.util.MouseHandler;
import blockerino.util.Vector2f;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player extends ControllableEntity {
	private String name;

	//region Class variables
	//TODO Load these from XML using JABX

	//endregion

	public Player() {
		Sprite _sprite = new Sprite(ResourceHandler.getLoadedTexture("player"), new Vector2f(1,1), new Vector2f(0,0));
		Vector2f _origin = new Vector2f(0, 0);
		int _size = 32;

		//TODO Set variables by loading from XML using JAXB


		/*, ,
				new Vector2f(1, 1)),
				new Vector2f(0, 0),
				3
*/
		//super(_sprite, _origin, _size);
	}

	private void move() {
		float dx = getDx();
		float dy = getDy();

		if(getSheetRowUp()) {
			dy -= acceleration;
			if(dy < -maxSpeed) {
				dy = -maxSpeed;
			}
		} else {
			if(dy < 0) {
				dy += deceleration;
				if(dy > 0) {
					dy = 0;
				}
			}
		}

		if(getDown()) {
			dy += acceleration;
			if(dy > maxSpeed) {
				dy = maxSpeed;
			}
		} else {
			if(dy > 0) {
				dy -= deceleration;
				if(dy < 0) {
					dy = 0;
				}
			}
		}

		if(getLeft()) {
			dx -= acceleration;
			if(dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		} else {
			if(dx < 0) {
				dx += deceleration;
				if(dx > 0) {
					dx = 0;
				}
			}
		}

		if(getRight()) {
			dx += acceleration;
			if(dx > maxSpeed) {
				dx = maxSpeed;
			}
		} else {
			if(dx > 0) {
				dx -= deceleration;
				if(dx < 0) {
					dx = 0;
				}
			}
		}

		setDx(dx);
		setDy(dy);

		//TODO Update Y position cause gravity
	}

	public void update() {
		super.update();
		move();
		position.x += getDx(); // get player X position
		position.y += getDy(); // get player Y position
	}

	@Override
	public void render(Graphics2D _graphics2D, AffineTransform _projectionViewMatrix) {
		Sprite s = new Sprite(new Texture(getAnimation().getImage()), position, new Vector2f(1, 1));
		s.render(_graphics2D, _projectionViewMatrix);
	}

	public void input(MouseHandler _mouse, KeyHandler _key) {
		setSheetRowUp(_key.up.down);
		setDown(_key.down.down);
		setLeft(_key.left.down);
		setRight(_key.right.down);

		setPrimaryUse(_mouse.button1.down);
		setSecondaryUse(_mouse.button2.down);
	}

}
