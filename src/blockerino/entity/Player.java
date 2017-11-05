package blockerino.entity;

import blockerino.graphics.Sprite;
import blockerino.util.KeyHandler;
import blockerino.util.MouseHandler;
import blockerino.util.Vector2f;

import java.awt.*;

public class Player extends Entity {

    public Player(Sprite _sprite, Vector2f _origin, int _size) {
        super(_sprite, _origin, _size);
    }

    public void move() {

        if (up) {
            dy -= acc;
            if (dy < -maxSpeed) {
                dy = -maxSpeed;
            }
        } else {
            if (dy < 0) {
                dy += deacc;
                if (dy > 0) {
                    dy = 0;
                }
            }
        }


        if (down) {
            dy += acc;
            if (dy > maxSpeed) {
                dy = maxSpeed;
            }
        } else {
            if (dy > 0) {
                dy -= deacc;
                if (dy < 0) {
                    dy = 0;
                }
            }
        }

        if (left) {
            dx -= acc;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else {
            if (dx < 0) {
                dx += deacc;
                if (dx > 0) {
                    dx = 0;
                }
            }
        }

        if (right) {
            dx += acc;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        } else {
            if (dx > 0) {
                dx -= deacc;
                if (dx < 0) {
                    dx = 0;
                }
            }
        }
    }

    public void update() {
        super.update();
        move();
        position.x += dx;
        position.y += dy;
    }

    @Override
    public void render(Graphics2D _graphics2D) {
        _graphics2D.drawImage(animation.getImage(), (int) position.x, (int) position.y, size, size, null);
    }

    public void input(MouseHandler _mouse, KeyHandler _key) {

        if (_key.up.down) {
            up = true;
        } else {
            up = false;
        }
        if (_key.down.down) {
            down = true;
        } else {
            down = false;
        }

        if (_key.left.down) {
            left = true;
        } else {
            left = false;
        }
        if (_key.right.down) {

        }
        if (_key.right.down) {
            right = true;
        } else {
            right = false;
        }

        if (_key.attack.down) {
            attack = true;
        } else {
            attack = false;
        }
    }
}
