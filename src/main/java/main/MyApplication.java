package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is the entry point of the GUI.
 */
public class MyApplication extends Application {

    /**
     * This is the entry point of the GUI.
     *
     * @param primaryStage The primary stage of the project.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/issues.fxml"));
        primaryStage.setTitle("Issue tracker");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
