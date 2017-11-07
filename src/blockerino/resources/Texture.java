package blockerino.resources;

import blockerino.util.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Texture holds either a single image buffer or an array of image buffers
 */
public class Texture extends Resource{
    private BufferedImage[] imageData;
    private String file;

    public Texture(int _id, String _name, String _file) {

        super(_id, _name);
        file = _file;

        System.out.println("Loading" + _file + "...");
        load();
    }

    public Texture(int _id, String _name, String _file, int _tileCols, int _tileRows) {

        super(_id, _name);
        file = _file;

        System.out.println("Loading" + _file + "...");
        load();

        splitImageData(_tileCols, _tileRows);
    }

    public void load(){
        try {
            imageData = new BufferedImage[1];
            imageData[0] = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        } catch (IOException e) {
            //TODO proper error handling
            System.out.println("ERROR" + e);
        }
    }

    public int getWidth() {
        return imageData[0].getWidth();
    }

    public int getHeight() {
        return imageData[0].getHeight();
    }

    public int getWidth(int _tileID) {
        return imageData[_tileID].getWidth();
    }

    public int getHeight(int _tileID) {
        return imageData[_tileID].getHeight();
    }

    /**
     * Split the image data into a grid of tiles.
     * @param _tileCols
     * @param _tileRows
     */
    private void splitImageData(int _tileCols, int _tileRows)
    {
        int tileCount = _tileCols * _tileRows;
        BufferedImage[] newImageData = new BufferedImage[tileCount];

        int chunkWidth = getWidth() / _tileCols;
        int chunkHeight = getHeight() / _tileRows;

        int i = 0;
        for (int y = 0; y < getHeight(); y += chunkHeight) {
            for (int x = 0; x < getWidth(); x += chunkWidth) {

                newImageData[i++] = imageData[0].getSubimage(x, y, chunkWidth, chunkHeight);
            }
        }

        imageData = newImageData;
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

    public BufferedImage getImageData() {
        return imageData[0];
    }

    public BufferedImage getImageData(int _i){
        return imageData[_i];
    }

    public BufferedImage[] getImageDataArray(){
        return imageData;
    }

    public BufferedImage[] getPartOfImageDataArray(int _start, int _end){
        return Arrays.copyOfRange(imageData, _start, _end);
    }

    /*
    public BufferedImage[] getRowOfImageDataArray(int _row){
        int start = 0;
        int end = 0;
    }
    */

    /*
    public BufferedImage getSprite(int _x, int _y) {
        return SPRITESHEET.getSubimage(_x * width, _y * height, width, height);
    }
    */

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