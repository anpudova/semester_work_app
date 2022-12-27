package org.example.listeners;

import org.example.exceptions.ServerException;
import org.example.models.Player;
import org.example.protocol.Message;
import org.example.protocol.MessageTypes;
import org.example.server.Connection;

import java.io.IOException;

public class ConnectClientListener extends AbstractServerEventListener {

    @Override
    public void handle(Connection connection, Message message) {
        if (message.getType() == MessageTypes.PLAYER_CONNECT) {
            String playerName;
            try {
                playerName = (String) message.deserialize(message.getData());
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            Player player = new Player((int) connection.getId(), playerName);
            connection.setPlayer(player);

            Message messageSuccess;
            Message messageRooms;
            try {
                messageSuccess = new Message();
                messageSuccess.setType(MessageTypes.CONNECTION_SUCCESSFUL);
                messageSuccess.setData(Message.serialize(player));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//                messageRooms = new Message();
//                messageRooms.setType(MessageTypes.LIST_ROOMS);
//                messageRooms.setData(Message.serialize(server.getRooms()));

            try {
                server.sendMessage(connection, messageSuccess);
//                server.sendMessageAllConnections(messageRooms);
            } catch (ServerException e) {
                throw new RuntimeException(e);
            }
        } else {

        }

    }

}
