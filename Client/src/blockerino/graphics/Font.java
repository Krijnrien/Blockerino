package blockerino.graphics;

import blockerino.util.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by krijn on 5/11/2017
 */
public class Font {
    private BufferedImage FONTSHEET = null;
    private BufferedImage[][] fontArray;
    private final int TILE_SIZE = 32;

    public int width;
    public int height;
    private int wLetter;
    private int hLetter;

    public Font(String _file) {
        width = TILE_SIZE;
        height = TILE_SIZE;

        System.out.println("Loading" + _file + "...");
        FONTSHEET = loadFont(_file);

        wLetter = FONTSHEET.getWidth() / width;
        hLetter = FONTSHEET.getHeight() / height;
    }

    public Font(String _file, int _w, int _h) {
        this.width = _w;
        this.height = _h;

        System.out.println("Loading" + _file + "...");
        FONTSHEET = loadFont(_file);

        wLetter = FONTSHEET.getWidth() / _w;
        hLetter = FONTSHEET.getWidth() / _h;
    }

    public void setSize(int _width, int _height) {
        setWidth(_width);
        setHeight(_height);
    }

    public void setWidth(int _width) {
        width = _width;
        wLetter = FONTSHEET.getWidth() / width;
    }

    public void setHeight(int _height) {
        height = _height;
        wLetter = FONTSHEET.getHeight() / height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BufferedImage loadFont(String _file) {
        BufferedImage font = null;
        try {
            font = ImageIO.read(getClass().getClassLoader().getResourceAsStream(_file));
        } catch (IOException e) {
            //TODO proper error handling
            System.out.println("ERROR" + e);
        }

        return font;
    }

    public void loadFontArray() {
        fontArray = new BufferedImage[wLetter][hLetter];
        for (int x = 0; x < wLetter; x++) {
            for (int y = 0; y < hLetter; y++) {
                fontArray[x][y] = getLetter(x, y);
            }
        }
    }

    public BufferedImage getFontSheet() {
        return FONTSHEET;
    }

    public BufferedImage getLetter(int _x, int _y) {
        return FONTSHEET.getSubimage(_x * width, _y * height, width, height);
    }

    public BufferedImage getFont(char _letter) {
        int value = _letter - 64;
        int x = value % wLetter;
        int y = value / hLetter;

        return getLetter(x, y);
    }

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


}

