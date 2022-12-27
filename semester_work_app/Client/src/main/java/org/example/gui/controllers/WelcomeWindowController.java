package org.example.gui.controllers;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.app.AppClient;
import org.example.client.ClientConnection;
import org.example.exceptions.ClientException;
import org.example.gui.helpers.SceneHelper;
import org.example.gui.helpers.Scenes;
import org.example.gui.services.ConnectClientService;
import org.example.models.Player;
import org.example.protocol.Message;
import org.example.protocol.MessageTypes;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class WelcomeWindowController {

    private static ClientConnection clientConnection;

    @FXML
    public Button btnPlay;
    @FXML
    public TextField name;

    private Stage stage = AppClient.getStage();
    private String playerName;

//    Stage stage = (Stage) btnPlay.getScene().getWindow();
//    stage.setScene(SceneHelper.getScene(Scenes.SCENE_ALL_ROOMS));

    public void clickPlay(ActionEvent actionEvent) throws UnknownHostException, ClassNotFoundException {
        playerName = name.getText();
        if (!playerName.equals("")) {
            try {
                setClientConnection(new ClientConnection(InetAddress.getLocalHost(), 6666));
            } catch (IOException | ClientException e) {
                errorConnection();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.NONE, "Для того, чтобы играть, нужно ввести имя");
            alert.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
            alert.show();
        }

    }

    public void setClientConnection(ClientConnection connection) {
        clientConnection = connection;
        new ConnectClientService(connection, this).start();
    }
    public static ClientConnection getClientConnection() {
        return clientConnection;
    }

    public void errorConnection() {
        Alert alert = new Alert(Alert.AlertType.NONE, "Ошибка подключения! попробуйте еще раз");
        alert.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
        alert.show();
    }

    public String getPlayerName() {
        return playerName;
    }

    public Stage getStage() {
        return stage;
    }

    public void setSceneWindow() {
        System.out.println("1111111111111111111111");
        try {
            Stage stage = (Stage) btnPlay.getScene().getWindow();
            stage.setScene(SceneHelper.getScene(Scenes.SCENE_ALL_ROOMS));
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
    }

}
