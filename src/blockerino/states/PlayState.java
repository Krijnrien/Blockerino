package blockerino.states;

import blockerino.util.*;

import java.awt.*;

public class PlayState extends GameState {

    public PlayState(GameStateManager _gameStateManager) {
        super(_gameStateManager);

    }

    public void update() {

    }

    public void input(MouseHandler _mouse, KeyHandler _key) {
        System.out.println("input");
            if(_key.up.down){
                System.out.println("w");
            }
    }

    public void render(Graphics2D _graphics2D) {
        _graphics2D.setColor(Color.RED);
        _graphics2D.fillRect(100, 100, 32, 32);
    }
}
