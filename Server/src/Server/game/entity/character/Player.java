package Server.game.entity.character;

import Server.game.entity.ControllableEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
// JAXB uses fields directly to create object and not getter and setter. Use @XmlValue above getter / setter to make JAXB use the getter/setter.
public class Player extends ControllableEntity {

    //region Class variables
    protected String name;
    //endregion

    private void move() {
        if (up && !topCollision.isColliding()) {
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

        if (down && !bottomCollision.isColliding()) {
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

        if (left && !leftCollision.isColliding()) {
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

        if (right && !rightCollision.isColliding()) {
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

    public void input() {
        //TODO Change to commands
        //        up = _key.up.down;
        //        down = _key.down.down;
        //        left = _key.left.down;
        //        right = _key.right.down;
        //
        //        primaryUse = _mouse.button1.down;
        //        secondaryUse = _mouse.button2.down;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
