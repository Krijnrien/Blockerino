package blockerino.UI.controllers;

import blockerino.ConfigProperties;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    Button buttonMultiplayer;

    //TODO Handle javafx IOexception exception?
    @FXML
    public void openMultiplayerScene() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(ConfigProperties.FXML + "menuMultiplayer.fxml"));
        Stage stage = (Stage) buttonMultiplayer.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

}
