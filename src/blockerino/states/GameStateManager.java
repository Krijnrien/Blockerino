package blockerino.states;

import java.awt.*;
import java.util.ArrayList;

import blockerino.util.*;

public class GameStateManager {

    public ArrayList<GameState> states;

    public GameStateManager() {
        states = new ArrayList<>();
        states.add(new PlayState(this));
    }

    public void update() {
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
