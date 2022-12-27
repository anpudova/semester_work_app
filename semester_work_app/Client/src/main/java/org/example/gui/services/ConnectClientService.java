package org.example.gui.services;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import org.example.client.ClientConnection;
import org.example.exceptions.ClientException;
import org.example.gui.controllers.WelcomeWindowController;
import org.example.gui.helpers.SceneHelper;
import org.example.gui.helpers.Scenes;
import org.example.models.Player;
import org.example.protocol.Message;
import org.example.protocol.MessageTypes;

import java.io.IOException;

public class ConnectClientService extends Service<Void> {

    private ClientConnection clientConnection;
    private WelcomeWindowController welcomeWindowController;

    public ConnectClientService(ClientConnection clientConnection, WelcomeWindowController welcomeWindowController) {
        this.clientConnection = clientConnection;
        this.welcomeWindowController = welcomeWindowController;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                boolean flag = pingPong(welcomeWindowController.getPlayerName());
                System.out.println(flag);
                if (flag) {
                     welcomeWindowController.setSceneWindow();
                }
                return null;
            }
        };
    }

    public boolean pingPong(String playerName) throws IOException, ClientException, ClassNotFoundException {
        Message messageS = new Message();
        messageS.setType(MessageTypes.PLAYER_CONNECT);

        messageS.setData(messageS.serialize(playerName));
        clientConnection.sendMessage(messageS);

        Message messageA = clientConnection.acceptMessage();
        if (messageA.getType() == MessageTypes.CONNECTION_SUCCESSFUL) {
            Player player = (Player) messageA.deserialize(messageA.getData());
            clientConnection.setPlayer(player);
            System.out.println(player.getName());
            return true;
        }
        return false;
    }

}