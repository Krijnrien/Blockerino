package blockerino.graphics.UI;

import blockerino.Window;
import blockerino.util.KeyHandler;


public class GameUI {
    private KeyHandler escapeKey;
    private EscapeGameUI escapeGameUI;

    public GameUI() {
        escapeGameUI = new EscapeGameUI();
    }

    public void input(KeyHandler _key) {
        escapeKey = _key;
    }

    public void update() {
        createMenu();
    }

    private void createMenu() {
        if (escapeKey.escape.toggle) {
            escapeGameUI.manageUI();
            escapeKey.escape.toggle = false;
        }
    }

}
