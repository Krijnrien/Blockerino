package blockerino.entity.character;

import blockerino.entity.ControllableEntity;
import blockerino.graphics.Sprite;
import blockerino.resources.Texture;
import blockerino.util.KeyHandler;
import blockerino.util.MouseHandler;
import blockerino.util.Vector2f;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.awt.*;
import java.awt.geom.AffineTransform;

@XmlRootElement
public class Player extends ControllableEntity {
	private String name;
	private String spriteSheet;

	//region Class variables
	//TODO Load these from XML using JABX

	//endregion

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
		Sprite s = new Sprite(new Texture(getAnimation().getImage()), position, scale);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(String spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
}
