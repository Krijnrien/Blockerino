package blockerino.entity;

import blockerino.combat.bullet.Bullet;
import blockerino.combat.bullet.projectile.StandardBullet;
import blockerino.graphics.Sprite;
import blockerino.resources.Texture;
import blockerino.util.KeyHandler;
import blockerino.util.MouseHandler;
import blockerino.util.Vector2f;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player extends Entity {

    //region Class variables
    private float weight;

    private float acceleration = 2f;
    private float deceleration = 0.3f;

    private float maxSpeed = 1 / 4f;

    private float size;
    //endregion

    public Player(Sprite _sprite, Vector2f _origin, int _size) {
        super(_sprite, _origin, _size);
    }

    private void move() {
        float dx = getDx();
        float dy = getDy();

        if (getSheetRowUp()) {
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

        if (getDown()) {
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

        if (getLeft()) {
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

        if (getRight()) {
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

        setDx(dx);
        setDy(dy);

        //TODO Update Y position cause gravity
    }

    private void interact() {

    }

    public void update() {
        super.update();
        move();
        position.x += getDx(); // get player X position
        position.y += getDy(); // get player Y position
    }

    public Vector2f getPosition(){
        return position;
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


        Bullet bullet = new StandardBullet();

//        try {
//            //TODO Find proper place to instantiate weapon & bullet. Reminder that weapons can be swapped in UI.
////            Class<?> weaponClass = Class.forName("RevolverPistol");
////            Constructor<?> constructor = weaponClass.getConstructor(Bullet.class);
////            Weapon weapon = (Weapon) constructor.newInstance(bullet);
////            weapon.input();
//        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
//            //TODO Proper error handling if gun class can't be found.
//            e.printStackTrace();
//        }


    }


    //region Getter & setters

    @Override
    public float getWeight() {
        return weight;
    }

    public void setWeight(float _weight) {
        this.weight = _weight;
    }

    public void setMaxSpeed(float _maxSpeed) {
        maxSpeed = _maxSpeed;
    }

    @Override
    public float getMaxSpeed() {
        return this.maxSpeed;
    }

    @Override
    public void setAcceleration(float _acceleration) {
        this.acceleration = _acceleration;
    }

    @Override
    public float getAcceleration() {
        return this.acceleration;
    }

    @Override
    public void setDeceleration(float _deceleration) {
        this.deceleration = _deceleration;
    }

    @Override
    public float getDeceleration() {
        return this.deceleration;
    }

    @Override
    public float getSize() {
        return this.size;
    }

    @Override
    public void setSize(float size) {
        this.size = size;
    }


    //endregion

}
