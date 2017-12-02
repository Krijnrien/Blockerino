package blockerino.UI.controllers;

import blockerino.ConfigProperties;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuMultiplayerController {

    @FXML
    TextField textFieldAddress;
    @FXML
    Button buttonCancel;

    @FXML
    public void Connect() {
        String[] address = textFieldAddress.getText().split(":", 2);
        String addressIP = address[0];
        String addressPort = address[1];
        System.out.println(addressIP + ":" + addressPort);
        //TODO heartbeat server
        //TODO verify connection
        //TODO Set loading scene?
    }

    //Todo handle exception?
    @FXML
    public void Cancel() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(ConfigProperties.FXML + "menu.fxml"));
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.setScene(new Scene(root));
    }


}
