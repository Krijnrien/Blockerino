package blockerino.states;

import blockerino.entity.Player;
import blockerino.graphics.Sprite;
import blockerino.util.*;
import blockerino.graphics.Font;

import java.awt.*;

public class PlayState extends GameState {

    private Player player;
    private Font font;

    public PlayState(GameStateManager _gameStateManager) {
        super(_gameStateManager);
        font = new Font("font/ZeldaFont.png", 16, 16);
        //TODO Handle possible file not found error
        player = new Player(new Sprite("entity/linkFormatted.png"), new Vector2f(300,300), 128);
    }

    public void update() {
        player.update();
    }

    public void input(MouseHandler _mouse, KeyHandler _key) {
        player.input(_mouse, _key);
    }

    public void render(Graphics2D _graphics2D) {
        Sprite.drawArray(_graphics2D, font, "APPLE", new Vector2f(100, 100), 32, 32, 16, 0);
        player.render(_graphics2D);
    }
}
