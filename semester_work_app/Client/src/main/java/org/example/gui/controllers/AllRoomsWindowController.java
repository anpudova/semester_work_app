package org.example.gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import org.example.models.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;


public class AllRoomsWindowController {
    @FXML
    public ListView<Integer> listRooms;
    public Integer numberRoom;


    public void init(ArrayList<Room> rooms) {
        List<Integer> roomsNumber = new ArrayList<>();
        for (Room room: rooms) {
            roomsNumber.add(room.getNumber());
        }
        ObservableList<Integer> obsListNumber = FXCollections.observableArrayList(roomsNumber);
        listRooms.setItems(obsListNumber);
    }

    @FXML
    public void clickJoinRoom() {
        TextInputDialog inputCodeRoomDialog = new TextInputDialog();
        inputCodeRoomDialog.setTitle("Присоединиться");
        inputCodeRoomDialog.setHeaderText("Введи номер комнаты");
        Optional<String> result = inputCodeRoomDialog.showAndWait();
        result.ifPresent(number -> {
            Pattern patt = Pattern.compile("[0-9]+");
            if (patt.matcher(number).find()) {
                numberRoom = Integer.valueOf(number);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Номер комнаты должен состоять из чисел.");
                alert.showAndWait();
            }
        });
    }

    @FXML
    public void clickCreateRoom() {

    }
}
