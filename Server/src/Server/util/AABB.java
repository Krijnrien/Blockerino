package Server.util;

import Server.entity.Entity;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class AABB {
    private Vector2f position;
    private Entity entity;
    private float xOffset = 0;
    private float yOffset = 0;
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

    public void setXOffset(float _xOffset) {
        xOffset = _xOffset;
    }

    public void setYOffset(float _yOffset) {
        this.yOffset = _yOffset;
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

        return (xDelta * xDelta + yDelta * yDelta) < ((this.radius / Math.sqrt((2)) * (this.radius / Math.sqrt(2))));

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
