package org.example.gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.client.ClientConnection;
import org.example.exceptions.ClientException;
import org.example.gui.helpers.SceneHelper;
import org.example.gui.helpers.Scenes;
import org.example.models.Room;
import org.example.protocol.Message;
import org.example.protocol.MessageTypes;

import java.io.IOException;
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

    private ClientConnection clientConnection = WelcomeWindowController.getClientConnection();

    public void initialize() {
        roomsId = new ArrayList<>();
        Message messageRooms = clientConnection.acceptMessage();
        if (messageRooms.getType() == MessageTypes.LIST_ROOMS) {
            List<Room> rooms;
            try {
                rooms = (List<Room>) messageRooms.deserialize(messageRooms.getData());
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            for (Room room: rooms) {
                roomsId.add(room.getId());
            }
            ObservableList<Integer> obsListNumber = FXCollections.observableArrayList(roomsId);
            listRooms.setItems(obsListNumber);
        }

    }

    @FXML
    public void clickJoinRoom(ActionEvent actionEvent) throws ClientException {
        Pattern pattern = Pattern.compile("[0-9]+");
        if (pattern.matcher(roomId.getText()).find()) {
            id = Integer.valueOf(roomId.getText());
            // потом убрать и раскомментировать
//            Stage stage = (Stage)btnJoin.getScene().getWindow();
//            stage.setScene(SceneHelper.getScene(Scenes.SCENE_WAIT_ROOM));

            if (roomsId.contains(id)) {
                Stage stage = (Stage)btnJoin.getScene().getWindow();
                stage.setScene(SceneHelper.getScene(Scenes.SCENE_WAIT_ROOM));
            } else {
                Alert alert = new Alert(Alert.AlertType.NONE, "Такой комнаты не существует");
                alert.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.NONE, "Номер комнаты состоит из чисел");
            alert.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
            alert.show();
        }
    }

    @FXML
    public void clickCreateRoom(ActionEvent actionEvent) throws ClientException, IOException {

        Room room = new Room();
        room.getAllPlayers().add(clientConnection.getPlayer());
        Message message = new Message();
        message.setType(MessageTypes.CREATE_ROOM);
        message.setData(message.serialize(room));
        clientConnection.sendMessage(message);

        Stage stage = (Stage)btnCreateRoom.getScene().getWindow();
        stage.setScene(SceneHelper.getScene(Scenes.SCENE_WAIT_ROOM));
    }

}
