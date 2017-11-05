package blockerino.states;

import blockerino.graphics.Sprite;
import blockerino.util.*;
import blockerino.graphics.Font;

import java.awt.*;

public class PlayState extends GameState {

    private Font font;

    public PlayState(GameStateManager _gameStateManager) {
        super(_gameStateManager);
        font = new Font("font/ZeldaFont.png", 16, 16);
    }

    public void update() {

    }

    public void input(MouseHandler _mouse, KeyHandler _key) {
    }

    public void render(Graphics2D _graphics2D) {
        Sprite.drawArray(_graphics2D, font, "APPLE", new Vector2f(100, 100), 32, 32, 16, 0);
    }
}
