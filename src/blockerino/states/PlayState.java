package blockerino.states;

import blockerino.entity.Player;
import blockerino.graphics.Sprite;
import blockerino.resources.ResourceHandler;
import blockerino.util.*;
import blockerino.graphics.Font;
import blockerino.world.World;
import blockerino.world.generation.NoiseGenerator;

import java.awt.*;

public class PlayState extends GameState {

    private static World world;
    private Player player;

    PlayState(GameStateManager _gameStateManager) {
        super(_gameStateManager);
        NoiseGenerator worldGen = new NoiseGenerator(1337);
        worldGen.setAmplitude(32);
        worldGen.setFrequency(16);
        worldGen.setAverageHeight(30);
        world = new World(16, worldGen);

        player = new Player(new Sprite(ResourceHandler.getLoadedTexture("player")), new Vector2f(300, 300), 128);
    }

    public void update() {
        player.update();
    }

    public void input(MouseHandler _mouse, KeyHandler _key) {
        player.input(_mouse, _key);
    }

    public void render(Graphics2D _graphics2D) {
        world.render(_graphics2D);
        player.render(_graphics2D);
    }
}
