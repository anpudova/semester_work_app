package org.example.gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import org.example.exceptions.ClientException;
import org.example.gui.helpers.SceneHelper;
import org.example.gui.helpers.Scenes;
import org.example.models.Player;

import java.util.ArrayList;
import java.util.List;

public class WaitRoomWindowController {
    public ListView<String> listPlayers;
    public List<String> players;
    public Button btnReady;

    public void init(ArrayList<Player> players) {
        this.players = new ArrayList<>();
        for (Player player: players) {
            this.players.add(player.getName());
        }
        ObservableList<String> obsListNumber = FXCollections.observableArrayList(this.players);
        listPlayers.setItems(obsListNumber);
    }

    public void clickReady(ActionEvent actionEvent) throws ClientException {
        btnReady.setStyle(
                "-fx-font-family: sans-serif; -fx-text-fill: #1D2671; -fx-font-size: 20; -fx-background-color: #bbbbbb"
        );
        // изменить потом
        Stage stage = (Stage)btnReady.getScene().getWindow();
        stage.setScene(SceneHelper.getScene(Scenes.SCENE_PHRASE_WRITE));
    }
}
