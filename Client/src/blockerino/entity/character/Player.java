package blockerino.entity.character;

import blockerino.entity.ControllableEntity;
import blockerino.graphics.Sprite;
import blockerino.resources.Texture;
import blockerino.util.KeyHandler;
import blockerino.util.MouseHandler;
import blockerino.util.Vector2f;

import javax.xml.bind.annotation.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD) // JAXB uses fields directly to create object and not getter and setter. Use @XmlValue above getter / setter to make JAXB use the getter/setter.
public class Player extends ControllableEntity {

    //region Class variables
    protected String name;
    //endregion

    private void move() {
        if (up) {
            dy -= acceleration;
            if (dy < -maxSpeed) {
                dy = -maxSpeed;
            }
        } else {
            if (dy < 0) {
                dy += deceleration;
                if (dy > 0) {
                    dy = 0;
                }
            }
        }

        if (down) {
            dy += acceleration;
            if (dy > maxSpeed) {
                dy = maxSpeed;
            }
        } else {
            if (dy > 0) {
                dy -= deceleration;
                if (dy < 0) {
                    dy = 0;
                }
            }
        }

        if (left) {
            dx -= acceleration;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else {
            if (dx < 0) {
                dx += deceleration;
                if (dx > 0) {
                    dx = 0;
                }
            }
        }

        if (right) {
            dx += acceleration;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        } else {
            if (dx > 0) {
                dx -= deceleration;
                if (dx < 0) {
                    dx = 0;
                }
            }
        }

        //TODO Update Y position cause gravity
    }

    public void update() {
        super.update();
        move();
        position.x += dx; // get player X position
        position.y += dy; // get player Y position
    }

    @Override
    public void render(Graphics2D _graphics2D, AffineTransform _projectionViewMatrix) {
        Sprite s = new Sprite(new Texture(animation.getImage()), position, scale);
        s.render(_graphics2D, _projectionViewMatrix);
    }

    public void input(MouseHandler _mouse, KeyHandler _key) {
        up = _key.up.down;
        down = _key.down.down;
        left = _key.left.down;
        right = _key.right.down;

        primaryUse = _mouse.button1.down;
        secondaryUse = _mouse.button2.down;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
