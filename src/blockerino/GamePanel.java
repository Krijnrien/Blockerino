package blockerino;

import blockerino.resources.ResourceHandler;
import blockerino.resources.Texture;
import blockerino.util.*;
import blockerino.states.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class GamePanel extends JPanel implements Runnable {

    public static int width;
    public static int height;

    private Thread thread;
    private boolean running = false;
    public static int fps = 60;

    private BufferedImage bufferedImage;
    private Graphics2D graphics2D;

    private MouseHandler mouseHandler;
    private KeyHandler keyHandler;

    private GameStateManager gameStateManager;

    GamePanel(int _width, int _height) {
        height = _height;
        width = _width;

        setPreferredSize(new Dimension(_width, _height));
    }

    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

    private void init() {
        running = true;

        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics2D = (Graphics2D) bufferedImage.getGraphics();


        Texture t_air = new Texture(1, "air", "blocks/air_temp.png");
        ResourceHandler.addTexture(t_air);

        Texture player = new Texture(2, "player", "entity/linkFormatted.png", 8, 4);
        ResourceHandler.addTexture(player);


        keyHandler = new KeyHandler(this);
        mouseHandler = new MouseHandler(this);
        requestFocus();
        gameStateManager = new GameStateManager();
    }

    @Override
    public void run() {
        init();

        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        double deltaTime = 0;
        requestFocus();
        //TODO Keylistener not working with constant requestFocus
        while (running) {
            double nanoSecondsPerCount = 1000000000.0 / fps;
            long now = System.nanoTime();
            deltaTime += (now - lastTime) / nanoSecondsPerCount;
            lastTime = now;
            while (deltaTime >= 1) {
                update();
                input(mouseHandler, keyHandler);
                deltaTime--;
            }
            input(mouseHandler, keyHandler);
            render();
            draw();
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }
        stop();
    }

    private int x = 0;

    private void input(MouseHandler _mouse, KeyHandler _key) {
        gameStateManager.input(_mouse, _key);
    }

    private void update() {
        gameStateManager.update();
    }

    private void render() {
        if (graphics2D != null) {
            graphics2D.setColor(new Color(66, 134, 244));
            graphics2D.fillRect(0, 0, width, height);
            gameStateManager.render(graphics2D);
        }
    }

    private void draw() {
        Graphics graphics = this.getGraphics();
        graphics.drawImage(bufferedImage, 0, 0, width, height, null);
        graphics.dispose();
    }


    public synchronized void stop() {
        running = false;
        try {
            System.out.println("Goodbye");
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
