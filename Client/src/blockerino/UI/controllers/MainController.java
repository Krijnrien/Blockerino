package blockerino.UI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private Button buttonLaunch;

    @FXML
    private TextField textFieldUsername;


    public void handleLaunchAction(ActionEvent event) {
        // Button was clicked
        System.out.println("testttt" + textFieldUsername.getText());
    }

}
