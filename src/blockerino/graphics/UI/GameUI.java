package blockerino.graphics.UI;


import blockerino.util.KeyHandler;

public class GameUI {

    KeyHandler escapeKey;

    public void input(KeyHandler _key) {
        escapeKey = _key;
    }

    public void update(){
        createMenu();
    }

    private void createMenu() {
        if (escapeKey.escape.toggle) {
            escapeKey.escape.toggle = false;
        }
    }

}
