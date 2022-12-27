package org.example.gui.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.exceptions.ClientException;
import org.example.gui.helpers.SceneHelper;
import org.example.gui.helpers.Scenes;

public class ResultWindowController {
    public Button btnExitRoom;
    public Button btnStartNewGame;

    public void clickExitRoom(ActionEvent actionEvent) throws ClientException {
        Stage stage = (Stage)btnExitRoom.getScene().getWindow();
        stage.setScene(SceneHelper.getScene(Scenes.SCENE_ALL_ROOMS));
    }

    public void clickStartNewGame(ActionEvent actionEvent) throws ClientException {
        Stage stage = (Stage)btnStartNewGame.getScene().getWindow();
        stage.setScene(SceneHelper.getScene(Scenes.SCENE_WAIT_ROOM));
    }
}
