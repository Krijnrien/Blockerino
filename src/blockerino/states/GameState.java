package blockerino.states;

import java.awt.*;

import blockerino.util.*;

/**
 * Created by Krijn on 4/11/2017
 */
public abstract class GameState {

    private GameStateManager gameStateManager;

    public GameState(GameStateManager _gameStateManager) {
        this.gameStateManager = _gameStateManager;

    }

    public abstract void update();

    public abstract void input(MouseHandler _mouse, KeyHandler _key);

    public abstract void render(Graphics2D _graphics2D);
}
