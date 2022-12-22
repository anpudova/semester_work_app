package org.example.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppClient extends Application {

    public static void main(String[] args) {
        AppClient.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Gartic Phone");
        stage.setWidth(800);
        stage.setHeight(600);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/layouts/AllRoomsWindow.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }
}