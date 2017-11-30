package blockerino;

import blockerino.states.GameStateManager;
import blockerino.util.KeyHandler;
import blockerino.util.LoadTextures;
import blockerino.util.MouseHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameLoop implements Runnable {
    private boolean running = false;

    private BufferedImage bufferedImage;
    private Graphics2D graphics2D;

    private MouseHandler mouseHandler;
    private KeyHandler keyHandler;

    private GameStateManager gameStateManager;
    private GamePanel gamePanel;

    GameLoop(GamePanel _gamePanel) {
        this.gamePanel = _gamePanel;
    }

    private void init() {
        running = true;

        bufferedImage = new BufferedImage(Window.width, Window.height, BufferedImage.TYPE_INT_ARGB);
        graphics2D = (Graphics2D) bufferedImage.getGraphics();

        LoadTextures loadTextures = new LoadTextures();
        loadTextures.loadTextures();
        loadTextures.loadBlocks();

        keyHandler = new KeyHandler(gamePanel);
        mouseHandler = new MouseHandler(gamePanel);
        gamePanel.requestFocus();
        gameStateManager = new GameStateManager();
    }

    @Override
    public void run() {
        init();

        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double deltaTime = 0;
        gamePanel.requestFocus();
        //TODO Keylistener not working without constant requestFocus
        while (running) {
            int fps = 60;
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

    private void input(MouseHandler _mouse, KeyHandler _key) {
        gameStateManager.input(_mouse, _key);
    }

    private void update() {
        gameStateManager.update();
    }

    private void render() {
        if (graphics2D != null) {
            graphics2D.setColor(new Color(66, 134, 244));
            graphics2D.fillRect(0, 0, Window.width, Window.height);
            gameStateManager.render(graphics2D);
        }
    }

    private void draw() {
        Graphics graphics = gamePanel.getGraphics();
        graphics.drawImage(bufferedImage, 0, 0, Window.width, Window.height, null);
        graphics.dispose();
    }

    private synchronized void stop() {
        running = false;
        System.out.println("Goodbye");
        //TODO Join thread
    }


}
