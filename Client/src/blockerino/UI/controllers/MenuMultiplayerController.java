package blockerino.UI.controllers;

import blockerino.ConfigProperties;
import blockerino.networking.handshake.Handshake;
import blockerino.networking.handshake.IClientValidation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import blockerino.auth.User;

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
        //TODO Set loading scene?

        try {
            Registry registry = LocateRegistry.getRegistry(addressIP, Integer.parseInt(addressPort));
            IClientValidation clientValidation = (IClientValidation) registry.lookup("handshake");
            clientValidation.sendHandshake(new Handshake(User.username));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Todo handle exception?
    @FXML
    public void Cancel() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(ConfigProperties.FXML + "menu.fxml"));
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
