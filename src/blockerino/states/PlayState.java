package blockerino.states;

import blockerino.entity.Player;
import blockerino.graphics.Sprite;
import blockerino.resources.ResourceHandler;
import blockerino.util.*;
import blockerino.graphics.Font;
import blockerino.world.Camera2D;
import blockerino.world.World;
import blockerino.world.generation.NoiseGenerator;

import java.awt.*;

public class PlayState extends GameState {

    public static World world;
    private Player player;
    private Camera2D camera;

    PlayState(GameStateManager _gameStateManager) {
        super(_gameStateManager);
        NoiseGenerator worldGen = new NoiseGenerator(1337);
        worldGen.setAmplitude(32);
        worldGen.setFrequency(16);
        worldGen.setAverageHeight(30);
        world = new World(16, worldGen);

        player = new Player(new Sprite(ResourceHandler.getLoadedTexture("player")), new Vector2f(10, 10), 32);

        camera = new Camera2D(1280); //TODO 1280 should be real screen width
        camera.setZoomValue(128);
        camera.setPosition(player.getPosition());
    }

    public void update() {
        player.update();
    }

    public void input(MouseHandler _mouse, KeyHandler _key) {
        player.input(_mouse, _key);
    }

    public void render(Graphics2D _graphics2D) {
        _graphics2D.setTransform(camera.getMatrix());

        world.render(_graphics2D);
        player.render(_graphics2D);
    }

    public Camera2D getCamera() {
        return camera;
    }
}
