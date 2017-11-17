package blockerino.states;

import blockerino.GamePanel;
import blockerino.entity.Player;
import blockerino.graphics.Sprite;
import blockerino.graphics.UI.GameUI;
import blockerino.resources.ResourceHandler;
import blockerino.util.*;
import blockerino.graphics.Font;
import blockerino.world.Camera2D;
import blockerino.world.World;
import blockerino.world.generation.NoiseGenerator;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class PlayState extends GameState {

    public static World world;
    private Player player;
    private Camera2D camera;
    private GameUI gameUI;

    private AffineTransform projectionMatrix;
    private AffineTransform projectionViewMatrix;

    PlayState(GameStateManager _gameStateManager) {
        super(_gameStateManager);
        NoiseGenerator worldGen = new NoiseGenerator(1337);
        worldGen.setAmplitude(32);
        worldGen.setFrequency(16);
        worldGen.setAverageHeight(30);
        world = new World(16, worldGen);
        gameUI = new GameUI();
        player = new Player(new Sprite(ResourceHandler.getLoadedTexture("player"), new Vector2f(0, 0), new Vector2f(1, 1)), new Vector2f(0, 0), 32);

        projectionMatrix = new AffineTransform();
        updateProjectionMatrix();

        camera = new Camera2D();
        camera.setScaleValue(80);
        camera.updateViewMatrixWidthOnly();

        projectionViewMatrix = new AffineTransform(projectionMatrix);
        updateProjectionViewMatrix();
        //camera.setPosition(player.getPosition());
    }

    public void update() {
        player.update();
        gameUI.update();
    }

    public void updateProjectionMatrix(){
        projectionMatrix.translate((double) GamePanel.width / 2, (double)GamePanel.height / 2);
    }

    public void updateProjectionViewMatrix(){
        //Combine projection matrix with camera matrix
        projectionViewMatrix.concatenate(camera.getViewMatrix());
    }

    public void input(MouseHandler _mouse, KeyHandler _key) {
        player.input(_mouse, _key);
        gameUI.input(_key);
    }

    public void render(Graphics2D _graphics2D) {
        world.render(_graphics2D, projectionViewMatrix);
        player.render(_graphics2D, projectionViewMatrix);
    }

    public Camera2D getCamera() {
        return camera;
    }
}
