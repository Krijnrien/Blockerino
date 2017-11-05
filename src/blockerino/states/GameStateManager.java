package blockerino.states;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

import blockerino.GamePanel;
import blockerino.util.*;

public class GameStateManager {

    public ArrayList<GameState> states;
    public static Vector2f map;

    public static final int PLAY = 0;
    public static final int MENU = 1;
    public static final int PAUSE = 2;
    public static final int GAMEOVER = 3;

    public GameStateManager() {
        map = new Vector2f(GamePanel.width, GamePanel.height);
        Vector2f.setWorldVar(map.x, map.y);

        states = new ArrayList<>();
        states.add(new PlayState(this));
    }

    public void pop(int _state) {
        states.remove(_state);
    }

    public void add(int _state) {

        switch (_state) {
            case PLAY:
                states.add(new PlayState(this));
                break;
            case MENU:
                states.add(new MenuState(this));
                break;
            case PAUSE:
                states.add(new PauseState(this));
                break;
            case GAMEOVER:
                states.add(new GameOverState(this));
                break;
        }
    }

    public void addAndPop(int _state) {
        states.remove(0);
        add(_state);
    }

    public void update() {
        Vector2f.setWorldVar(map.x, map.y);
        for (GameState state : states) {
            state.update();
        }
    }

    public void input(MouseHandler _mouse, KeyHandler _key) {
        for (GameState state : states) {
            state.input(_mouse, _key);
        }
    }

    public void render(Graphics2D _graphics2D) {
        for (GameState state : states) {
            state.render(_graphics2D);
        }
    }

}
