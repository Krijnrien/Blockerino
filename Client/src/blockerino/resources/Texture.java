package blockerino.resources;

import blockerino.util.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

/**
 * Texture holds either a single image buffer or an array of image buffers
 */
public class Texture{
    private BufferedImage[] imageData;
    private String file;

    public Texture(String _file) {

        file = _file;

        System.out.println("Loading" + _file + "...");
        load();
    }

    public Texture(String _file, int _tileCols, int _tileRows) {

        file = _file;

        System.out.println("Loading" + _file + "...");
        load();

        splitImageData(_tileCols, _tileRows);
    }

    public Texture(BufferedImage _imageData){
        imageData = new BufferedImage[1];
        imageData[0] = _imageData;
    }

    public void load(){
        try {
            imageData = new BufferedImage[1];
            imageData[0] = ImageIO.read(getClass().getResourceAsStream(file));
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

    public void drawOnImage(BufferedImage[] _images, Vector2f[] _positions){

        Graphics g = imageData[0].getGraphics();

        //Calculate new width and height
        for (int i = 0; i < _images.length; i++) {
            g.drawImage(_images[i], (int)_positions[i].x, (int)_positions[i].y, null);
        }
    }

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

}