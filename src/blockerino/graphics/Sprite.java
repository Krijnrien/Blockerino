package blockerino.graphics;

import blockerino.resources.Texture;
import blockerino.util.Vector2f;

import java.awt.*;

public class Sprite {

    private Vector2f position;
    private Vector2f scale;

    private Texture texture;

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

    public Sprite(Texture _texture, Vector2f _position, Vector2f _scale){

        texture = _texture;
        position = _position;
        scale = _scale;
    }

    /*
    public Sprite(String _file, int _w, int _h) {
        this.width = _w;
        this.height = _h;
    }
    */

    public void render(Graphics2D _graphics2D) {
        _graphics2D.drawImage(texture.getSpriteSheet(), (int) position.x, (int) position.y, (int)scale.x, (int)scale.y, null);
    }

    public void setSize(int _width, int _height) {
        position.x = _width;
        position.y = _height;
    }

    public int getWidth() {
        return (int)scale.x;
    }

    public int getHeight() {
        return (int)scale.y;
    }
}

