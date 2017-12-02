package blockerino;

import blockerino.UI.StageFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        StageFactory factory = StageFactory.getInstance();
        factory.registerStage(primaryStage);

        configureStage(primaryStage);
        primaryStage.show();
    }

    //TODO Handle exception?
    private void configureStage(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UI/fxml/launcher.fxml"));
        stage.setScene(new Scene(root));
        stage.setMinWidth(620);
        stage.setMinHeight(440);
    }
}