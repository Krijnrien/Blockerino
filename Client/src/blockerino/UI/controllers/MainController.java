package blockerino.UI.controllers;

import blockerino.ConfigProperties;
import blockerino.UI.StageFactory;
import blockerino.auth.Auth;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private TextField textFieldUsername;
    @FXML
    private TextField textFieldPassword;

    public void handleLaunchAction(ActionEvent event) throws IOException {
        if (new Auth().authenticateUser(textFieldPassword.getText(), textFieldUsername.getText())) {
            launchGameContainer();
            // Hide current window
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } else {
            System.out.println("Auth failed");
        }
    }

    //TODO Handle exception?
    public void launchGameContainer() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(ConfigProperties.FXML + "menu.fxml"));
        StageFactory factory = StageFactory.getInstance();
        Stage stage = factory.createStage(root);
        stage.show();
    }
}
