package blockerino.graphics;

import blockerino.util.Vector2f;

import java.awt.*;

public class Circle {
    private Graphics2D graphics;
    protected Vector2f radius;


    public Circle(Graphics2D _graphics2D, Vector2f _radius) {
        this.graphics = _graphics2D;
        this.radius = _radius;
    }

    public void renderCircle(double x, double y, double r, Color color) {
        graphics.setColor(color);
        graphics.drawOval((int) (x - r + 1280 / 2), (int) (y - r + 256 / 2), (int) (2 * r), (int) (2 * r));
    }

}
