package blockerino;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class GamePanel extends JPanel implements Runnable {

    private static int width;
    private static int height;

    private Thread thread;
    private boolean running = false;
    public static int fps = 60;

    private BufferedImage bufferedImage;
    private Graphics2D graphics2D;

    GamePanel(int _width, int _height) {
        height = _height;
        width = _width;

        setPreferredSize(new Dimension(_width, _height));
        setFocusable(true);
        requestFocus();
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
    }

    @Override
    public void run() {
        init();

        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        double deltaTime = 0;
        requestFocus();
        while (running) {
            double nanoSecondsPerCount = 1000000000.0 / fps;
            long now = System.nanoTime();
            deltaTime += (now - lastTime) / nanoSecondsPerCount;
            lastTime = now;
            while (deltaTime >= 1) {
                update();
                deltaTime--;
            }
            render();
            draw();
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }
        stop();
    }

    private int x = 0;

    private void input() {

    }

    private void update() {
        x++;
        System.out.println(x);
    }

    private void render() {
        if (graphics2D != null) {
            graphics2D.setColor(new Color(66, 134, 244));
            graphics2D.fillRect(0, 0, width, height);
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
