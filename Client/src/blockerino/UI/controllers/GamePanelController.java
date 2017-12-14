package blockerino.UI.controllers;

import blockerino.GameLoop;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;

public class GamePanelController {

    @FXML
    public StackPane stackPaneGameParent;
    @FXML
    public Canvas canvasGamePanel;

    @FXML
    public void initialize() {
//        GameLoop gameLoop = new GameLoop(this);
//        Thread t = new Thread(gameLoop, "GameThread");
//        t.start();
    }
}
