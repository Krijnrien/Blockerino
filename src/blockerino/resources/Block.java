package blockerino.resources;

import blockerino.util.AABB;
import blockerino.util.Vector2f;

import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class Block {

    private Texture texture = null;

    private boolean hasCollision;
    private boolean solid = true;

    private AABB collision;

    public Block() {
        setCollision(new AABB(new Vector2f(0, 0), 1, 1));
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture _texture) {
        texture = _texture;
    }

    public void setCollision(AABB _collision){
        collision = _collision;
        hasCollision = true;
    }

    public AABB getCollision() {
        return collision;
    }

    public void setSolid(boolean _solid) {
        this.solid = _solid;
    }

    public boolean getSolid() {
        return this.solid;
    }

    public boolean hasCollision(){
        return hasCollision;
    }

    public void renderCollision(Graphics2D _graphics2D, AffineTransform _projectionViewMatrix, Vector2f _worldPos){
        AffineTransform matrix = new AffineTransform();

        AffineTransform transformCollisionMatrix = new AffineTransform();
        transformCollisionMatrix.translate(_worldPos.x, _worldPos.y);
        transformCollisionMatrix.scale(collision.getWidth(), collision.getHeight());

        // Create MVP matrix with Projection-View matrix and transform matrix
        matrix.concatenate(_projectionViewMatrix);
        matrix.concatenate(transformCollisionMatrix);

        //transform matrix position to top left of the sprite and scale down to model scale
        matrix.translate(-0.5f, -0.5f);
        matrix.scale((double)1 / 32, (double)1 / 32);

        //Create copy of Graphics2D and set matrix;
        Graphics2D g = (Graphics2D)_graphics2D.create();
        g.setTransform(matrix);
        g.setColor(new Color(0, 255, 0));
        g.drawRect(0, 0, 32, 32);
    }
}
