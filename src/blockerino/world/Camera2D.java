package blockerino.world;

import blockerino.util.Vector2f;

import java.awt.geom.AffineTransform;

public class Camera2D {
    private AffineTransform matrix = null;
    private Vector2f position;
    private double zoomValue;
    private int screenWidth;

    public Camera2D(int _screenWidth){
        matrix = new AffineTransform();
        position = new Vector2f();
        screenWidth = _screenWidth;
    }

    public void setZoomValue(double _value){
        zoomValue = _value;
        matrix.scale(screenWidth / zoomValue, screenWidth / zoomValue);
    }

    public double getZoomValue(){
        return zoomValue;
    }

    public void setPosition(Vector2f _position){
        position = _position;
        matrix.translate(_position.x, _position.y);
    }

    public Vector2f getPosition() {
        return position;
    }

    public AffineTransform getMatrix() {
        return matrix;
    }
}
