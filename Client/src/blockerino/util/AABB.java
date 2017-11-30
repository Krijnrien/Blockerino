package blockerino.util;

import blockerino.entity.Entity;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class AABB {
    private Vector2f position;
    private Entity entity;
    private float width;
    private float height;

    private float radius;
    private boolean isColliding;

    public AABB(Vector2f _position, float _width, float _height) {
        this.position = _position;
        this.width = _width;
        this.height = _height;
        //collidingObjects = new ArrayList<>();
    }

    public AABB(Vector2f _position, int _radius, Entity _entity) {
        this.position = _position;
        this.radius = _radius;
        this.entity = _entity;
        //collidingObjects = new ArrayList<>();
    }

    public void setPosition(Vector2f _position){
        position = _position;
    }

    public Vector2f getPosition() {
        return position;
    }

    public float getRadius() {
        return radius;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setBox(Vector2f _position, int _width, int _height) {
        this.position = _position;
        this.width = _width;
        this.height = _height;
    }

    public void setCircle(Vector2f _position, int _radius) {
        this.position = _position;
        this.radius = _radius;
    }

    public void setWidth(float _width) {
        width = _width;
    }

    public void setHeight(float _height) {
        height = _height;
    }

    public boolean collides(AABB bBox) {
        float ax = ((position.getWorldVar().x) + (width / 2));
        float ay = ((position.getWorldVar().y) + (height / 2));
        float bx = ((bBox.getPosition().getWorldVar().x + (width / 2)));
        float by = ((bBox.getPosition().getWorldVar().y + (height / 2)));

        if (Math.abs(ax - bx) < (this.width / 2) + (bBox.width / 2)) {
            if (Math.abs(ay - by) < (this.height / 2) + (bBox.height / 2)) {
                return true;
            }
        }
        return false;

    }

    public boolean colCircleBox(AABB aBox) {
        float cx = (float) (position.getWorldVar().x + radius / Math.sqrt(2) - entity.getSize().x / Math.sqrt(2));
        float cy = (float) (position.getWorldVar().y + radius / Math.sqrt(2) - entity.getSize().y / Math.sqrt(2));

        float xDelta = cx - Math.max(aBox.position.getWorldVar().x + (aBox.getWidth() / 2), Math.min(cx, aBox.position.getWorldVar().x));
        float yDelta = cx - Math.max(aBox.position.getWorldVar().y + (aBox.getHeight() / 2), Math.min(cx, aBox.position.getWorldVar().y));

        if ((xDelta * xDelta + yDelta * yDelta) < ((this.radius / Math.sqrt((2)) * (this.radius / Math.sqrt(2))))) {
            return true;
        }

        return false;
    }

    public void render(Graphics2D _graphics2D, AffineTransform _projectionViewMatrix){
        AffineTransform matrix = new AffineTransform();

        AffineTransform transformCollisionMatrix = new AffineTransform();
        transformCollisionMatrix.translate(position.x, position.y);
        transformCollisionMatrix.scale(width, height);

        // Create MVP matrix with Projection-View matrix and transform matrix
        matrix.concatenate(_projectionViewMatrix);
        matrix.concatenate(transformCollisionMatrix);

        //transform matrix position to top left of the sprite and scale down to model scale
        matrix.translate(-0.5f, -0.5);
        matrix.scale((double)1 / 32, (double)1 / 32);

        //Create copy of Graphics2D and set matrix;
        Graphics2D g = (Graphics2D)_graphics2D.create();
        g.setTransform(matrix);

        if (isColliding){
            g.setColor(new Color(255, 0, 0));
            //isColliding = false;
        } else {
            g.setColor(new Color(0, 255, 0));
        }
        g.drawRect(0, 0, 32, 32);
    }

    public void setColliding(boolean _state){
        if (isColliding != _state) {
            isColliding = _state;
        } //else do nothing
    }

    public boolean isColliding(){
        return isColliding;
    }
}
