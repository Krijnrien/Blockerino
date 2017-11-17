package blockerino.graphics;

import blockerino.GamePanel;
import blockerino.resources.Texture;
import blockerino.states.PlayState;
import blockerino.util.Vector2f;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class Sprite {

    private Vector2f position;
    private Vector2f scale;

    private AffineTransform transformMatrix;

    private Texture texture;
    private Vector2f texturePosition = new Vector2f(-0.5f, -0.5f);

    /*
    public Sprite(Texture _texture) {

        texture = _texture;
        scale = new Vector2f(texture.getWidth(), texture.getHeight());

        position = new Vector2f(0, 0);
    }

    public Sprite(Texture _texture, Vector2f _scale) {

        texture = _texture;
        scale = _scale;

        position.x = texture.getWidth() / position.x;
        position.y = texture.getHeight() / position.y;
    }
    */
    public Sprite(Texture _texture, Vector2f _position, Vector2f _scale){

        texture = _texture;
        position = _position;
        scale = _scale;

        transformMatrix = new AffineTransform();
        updateMatrix();
    }

    /*
    public Sprite(String _file, int _w, int _h) {
        this.width = _w;
        this.height = _h;
    }
    */

    public Texture getTexture(){
        return texture;
    }

    public void render(Graphics2D _graphics2D, AffineTransform _projectionViewMatrix) {
        AffineTransform matrix = new AffineTransform();

        // Create MVP matrix with Projection-View matrix and transform matrix
        matrix.concatenate(_projectionViewMatrix);
        matrix.concatenate(transformMatrix);

        //Copy current matrix to circle matrix. At this point the matrix is at the center of the sprite
        AffineTransform circleMatrix = new AffineTransform(matrix);

        //transform matrix position to top left of the sprite and scale down to model scale
        matrix.translate(texturePosition.x, texturePosition.y);
        matrix.scale((float)1 / (texture.getWidth()),(float)1 / (texture.getHeight()));

        _graphics2D.drawImage(texture.getImageData(), matrix,null);


        /* Rendering Debugging Tools */

        //transform circle draw position so the circle stays centered. Also scale down circle to model scale
        circleMatrix.scale((float)1 / (texture.getWidth()),(float)1 / (texture.getHeight()));
        circleMatrix.translate(-2, -2);

        //Create copy of Graphics2D and set matrix;
        Graphics2D g = (Graphics2D)_graphics2D.create();
        g.setTransform(circleMatrix);
        g.setColor(new Color(15, 88, 255));
        g.fillOval(0, 0, 4, 4);
    }

    public AffineTransform getTransformMatrix() {
        return transformMatrix;
    }

    public void updateMatrix(){
        transformMatrix.translate(position.x, position.y);
        transformMatrix.scale(scale.x, scale.y);
    }

    public void setPosition(Vector2f _position){
        position = _position;
    }

    public void setScale(Vector2f _scale) {
        scale = _scale;
    }

    public Vector2f getPosition() {
        return position;
    }

    public Vector2f getScale() {
        return scale;
    }

    public int getWidth() {
        return (int)scale.x;
    }

    public int getHeight() {
        return (int)scale.y;
    }
}