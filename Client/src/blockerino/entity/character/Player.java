package blockerino.entity.character;

import blockerino.entity.ControllableEntity;
import blockerino.graphics.Circle;
import blockerino.graphics.Sprite;
import blockerino.resources.Texture;
import blockerino.util.KeyHandler;
import blockerino.util.MouseHandler;
import blockerino.util.Vector2f;

import javax.xml.bind.annotation.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
// JAXB uses fields directly to create object and not getter and setter. Use @XmlValue above getter / setter to make JAXB use the getter/setter.
public class Player extends ControllableEntity {

    //region Class variables
    protected String name;
    //endregion

    private void move() {
        testCollision();

        //apply gravity
        if (dy < 1f) {
            dy += 0.02f;
        }

        if (!topCollision.isColliding()) {
            if (up) {
                if (bottomCollision.isColliding()) {
                    dy = -0.5f;
                }
            }
        } else {
            if (dy < 0) {
                dy = 0;
            }
        }

        if (bottomCollision.isColliding()) {
            if (dy > 0) {
                dy = 0;
            }
        }

        if (!leftCollision.isColliding()) {
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

            dx += velocity.x;

        } else {
            dx = 0;
        }

        if (!rightCollision.isColliding()) {
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

            dx += velocity.x;

        } else {
            if (dx > 0) {
                dx = 0;
            }
        }

        //TODO Update Y position cause gravity
    }

    public void update() {
        super.update();

        move();

        position.x += dx; // get player X position
        position.y += dy; // get player Y position

        updateCollisions();
    }

    @Override
    public void render(Graphics2D _graphics2D, AffineTransform _projectionViewMatrix) {
        Sprite s = new Sprite(new Texture(animation.getImage()), position, scale);
        s.render(_graphics2D, _projectionViewMatrix);
        Circle circle = new Circle(_graphics2D, new Vector2f(1,1));
        circle.renderCircle(position.x, position.y, 10, Color.BLUE);
    }

    public void input(MouseHandler _mouse, KeyHandler _key) {
        up = _key.up.down;
        down = _key.down.down;
        left = _key.left.down;
        right = _key.right.down;

        primaryUse = _mouse.button1.down;
        secondaryUse = _mouse.button2.down;
    }

}
