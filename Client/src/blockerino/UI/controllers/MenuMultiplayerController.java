package blockerino.UI.controllers;

import Server.connection.CommandServer;
import blockerino.CommandClient;
import blockerino.ConfigProperties;
import blockerino.GamePanel;
import blockerino.Window;
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
    Button buttonConnect;

    //Todo handle exception?
    @FXML
    public void Connect() throws IOException {
        String[] address = textFieldAddress.getText().split(":", 2);
        String addressIP = address[0];
        int addressPort = Integer.parseInt(address[1]);
        System.out.println(addressIP + ":" + addressPort);
        //TODO Set loading scene?

//        try {
//            Registry registry = LocateRegistry.getRegistry(addressIP, Integer.parseInt(addressPort));
//            IClientValidation clientValidation = (IClientValidation) registry.lookup("handshake");
//            clientValidation.sendHandshake(new Handshake(User.username));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        System.out.println("Starting client kryo"); //TODO add proper logging
        CommandClient commandClient = new CommandClient(addressIP, addressPort);
        Thread connectionThread = new Thread(commandClient, "connectionThread");
        connectionThread.start();

        Parent root = FXMLLoader.load(getClass().getResource(ConfigProperties.FXML + "GamePanel.fxml"));
        Stage stage = (Stage) buttonConnect.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    //Todo handle exception?
    @FXML
    public void Cancel() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(ConfigProperties.FXML + "menu.fxml"));
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
