package blockerino.world;

import blockerino.GamePanel;
import blockerino.Window;
import blockerino.entity.Entity;
import blockerino.util.Vector2f;

import java.awt.geom.AffineTransform;

/**
 * Camera2D is an matrix (AffineTransform) wrapper for camera movement
 * updateMatrix needs to be called to update the matrix
 */
public class Camera2D {
    private AffineTransform viewMatrix = null;
    private Vector2f position;
    private Vector2f scale;

    private float x = 0;

    private Entity target;

    public Camera2D(){
        viewMatrix = new AffineTransform();
        position = new Vector2f();
        scale = new Vector2f(20, 20);
    }

    public void setScaleValue(float _value){
        scale.x = _value;
        scale.y = _value;
    }

    public void setScale(Vector2f _scale){
        scale = _scale;
    }

    public Vector2f getScale(){
        return scale;
    }

    public void setPosition(Vector2f _position){
        position = _position;
    }

    public void setTarget(Entity _target){
        target = _target;
    }

    public Entity getTarget(){
        return target;
    }

    public void clearTarget(){
        target = null;
    }

    public void updateViewMatrixWidthOnly(){

        viewMatrix = new AffineTransform();
        viewMatrix.scale((double)Window.width / scale.x, (double) Window.width / scale.y);

        if (target != null){
            viewMatrix.translate(-target.getPosition().x, -target.getPosition().y);
        } else {
            viewMatrix.translate(-position.x, -position.y);
        }
    }

    public Vector2f getPosition() {
        return position;
    }

    public AffineTransform getViewMatrix() {
        return viewMatrix;
    }
}