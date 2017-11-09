package blockerino.states;

import java.awt.*;
import java.util.HashMap;

import blockerino.GamePanel;
import blockerino.util.*;


public class GameStateManager {

    private HashMap<GameStateEnum, GameState> states;
    private static Vector2f map;


    public GameStateManager() {
        map = new Vector2f(GamePanel.width, GamePanel.height);
        Vector2f.setWorldVar(map.x, map.y);

        states = new HashMap<>();
        states.put(GameStateEnum.PLAY, new PlayState(this));
    }

    public void pop(GameStateEnum _stateEnum) {
        states.remove(_stateEnum);
    }

    public void add(GameStateEnum _stateEnum) {
        //TODO check if gameState is already in hashmap.
        switch (_stateEnum) {
            case PLAY:
                states.put(GameStateEnum.PLAY, new PlayState(this));
                break;
            case MENU:
                states.put(GameStateEnum.MENU, new MenuState(this));
                break;
            case PAUSE:
                states.put(GameStateEnum.PAUSE, new PauseState(this));
                break;
            case GAMEOVER:
                states.put(GameStateEnum.GAMEOVER, new GameOverState(this));
                break;
        }
    }

    public void addAndPop(GameStateEnum _stateEnumPop, GameStateEnum _stateEnumAdd) {
        states.remove(_stateEnumPop);
        add(_stateEnumAdd);
    }

    public void update() {
        Vector2f.setWorldVar(map.x, map.y);
        for (GameState state : states.values()) {
            state.update();
        }
    }

    public void input(MouseHandler _mouse, KeyHandler _key) {
        for (GameState state : states.values()) {
            state.input(_mouse, _key);
        }
    }

    public void render(Graphics2D _graphics2D) {
        for (GameState state : states.values()) {
            state.render(_graphics2D);
        }
    }

}
