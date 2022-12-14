package org.example.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppClient extends Application {

    private static Stage stage;

    public static void main(String[] args) {
        AppClient.launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        setStage(stage);
        stage.setTitle("Gartic Phone");
        stage.setHeight(800);
        stage.setWidth(1100);
        stage.setMinHeight(800);
        stage.setMinWidth(1100);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/layouts/WelcomeWindow.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        AppClient.stage = stage;
    }
}