package Server.game.util;

/**
 * Created by Krijn on 5/11/2017
 */
public class Vector2f {

    public float x;
    public float y;

    private static float worldX;
    private static float worldY;

    public Vector2f() {
        x = 0;
        y = 0;
    }

    public Vector2f(Vector2f _position) {
        new Vector2f(_position.x, _position.y);

    }

    public Vector2f(float _x, float _y) {
        this.x = _x;
        this.y = _y;
    }

    public void addX(float _f) {
        x += _f;
    }

    public void addY(float _f) {
        y += _f;
    }

    public void setX(float _x) {
        x = _x;
    }

    public void setY(float _y) {
        y = _y;
    }

    public void setVector(Vector2f _vector2f) {
        this.x = _vector2f.x;
        this.y = _vector2f.y;
    }

    public void setVector(float _x, float _y) {
        this.x = _x;
        this.y = _y;
    }

    public static void setWorldVar(float _x, float _y) {
        worldX = _x;
        worldY = _y;
    }

    public Vector2f getWorldVar() {
        return new Vector2f(x - worldX, y - worldY);
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }
}
