package org.example.gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.exceptions.ClientException;
import org.example.gui.helpers.SceneHelper;
import org.example.gui.helpers.Scenes;
import org.example.models.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class AllRoomsWindowController {
    public ListView<Integer> listRooms;
    public Integer id;
    public TextField roomId;
    public Button btnJoin;
    public Button btnCreateRoom;
    public List<Integer> roomsId;


    public void init(ArrayList<Room> rooms) {
        roomsId = new ArrayList<>();
        for (Room room: rooms) {
            roomsId.add(room.getNumber());
        }
        ObservableList<Integer> obsListNumber = FXCollections.observableArrayList(roomsId);
        listRooms.setItems(obsListNumber);
    }

    @FXML
    public void clickJoinRoom() throws ClientException {
        Pattern pattern = Pattern.compile("[0-9]+");
        if (pattern.matcher(roomId.getText()).find()) {
            id = Integer.valueOf(roomId.getText());
            // потом убрать и раскомментировать
            Stage stage = (Stage)btnJoin.getScene().getWindow();
            stage.setScene(SceneHelper.getScene(Scenes.SCENE_WAIT_ROOM));
            /*
            if (roomsId.contains(id)) {
                Stage stage = (Stage)btnJoin.getScene().getWindow();
                stage.setScene(SceneHelper.getScene(Scenes.SCENE_WAIT_ROOM));
            } else {
                Alert alert = new Alert(Alert.AlertType.NONE, "Такой комнаты не существует");
                alert.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
                alert.show();
            }*/
        } else {
            Alert alert = new Alert(Alert.AlertType.NONE, "Номер комнаты состоит из чисел");
            alert.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
            alert.show();
        }
    }

    @FXML
    public void clickCreateRoom() throws ClientException {
        Stage stage = (Stage)btnCreateRoom.getScene().getWindow();
        stage.setScene(SceneHelper.getScene(Scenes.SCENE_WAIT_ROOM));
    }
}
