package blockerino.resources;

import blockerino.util.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Texture extends Resource{
    private BufferedImage SPRITESHEET = null;
    private final int TILE_SIZE = 32;

    public int width;
    public int height;

    public Texture(int _id, String _name, String _file) {

        super(_id, _name);

        width = TILE_SIZE;
        height = TILE_SIZE;

        System.out.println("Loading" + _file + "...");
        SPRITESHEET = loadSprite(_file);

        //loadSpriteArray();
    }

    @Override
    public void load() {
        super.load();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BufferedImage loadSprite(String _file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(_file));
        } catch (IOException e) {
            //TODO proper error handling
            System.out.println("ERROR" + e);
        }

        return sprite;
    }

    /*
    public void loadSpriteArray() {
        spriteArray = new BufferedImage[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                spriteArray[y][x] = getSprite(x, y);
            }
        }
    }
    */

    public BufferedImage getSpriteSheet() {
        return SPRITESHEET;
    }

    public BufferedImage getSprite(int _x, int _y) {
        return SPRITESHEET.getSubimage(_x * width, _y * height, width, height);
    }

    /*
    public BufferedImage[] getSpriteFromSpriteArray(int _i) {
        return spriteArray[_i];

    }
    */

    /*
    public BufferedImage[][] getSpriteArray(int _i) {
        return spriteArray;
    }
    */

    /*
    public static void drawArray(Graphics2D graphics2D, ArrayList<BufferedImage> img, Vector2f vector2f, int width, int height, int xOffset, int yOffset) {
        float x = vector2f.x;
        float y = vector2f.y;

        for (int i = 0; i < img.size(); i++) {
            if (img.get(i) != null) {
                graphics2D.drawImage(img.get(i), (int) x, (int) y, width, height, null);
            }

            x += xOffset;
            y += yOffset;
        }
    }

    public static void drawArray(Graphics2D _graphics2D, Font _font, String _word, Vector2f _vector2f, int _width, int _height, int _xOffset, int _yOffset) {
        float x = _vector2f.x;
        float y = _vector2f.y;

        for (int i = 0; i < _word.length(); i++) {
            if (_word.charAt(i) != 32) {
                _graphics2D.drawImage(_font.getFont(_word.charAt(i)), (int) x, (int) y, _width, _height, null);
            }
            x += _xOffset;
            y += _yOffset;
        }


    }
    */
}