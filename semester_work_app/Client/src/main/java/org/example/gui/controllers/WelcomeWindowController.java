package org.example.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.exceptions.ClientException;
import org.example.gui.helpers.SceneHelper;
import org.example.gui.helpers.Scenes;

public class WelcomeWindowController {

    @FXML
    public Button btnPlay;
    public TextField name;

    public void clickPlay(ActionEvent actionEvent) throws ClientException {
        if (!name.getText().equals("")) {
            Stage stage = (Stage)btnPlay.getScene().getWindow();
            stage.setScene(SceneHelper.getScene(Scenes.SCENE_ALL_ROOMS));
        } else {
            Alert alert = new Alert(Alert.AlertType.NONE, "Для того, чтобы играть, нужно ввести имя");
            alert.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
            alert.show();
        }
    }
}
