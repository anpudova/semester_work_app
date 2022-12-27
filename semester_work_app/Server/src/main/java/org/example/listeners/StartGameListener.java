package org.example.listeners;

import org.example.exceptions.ServerException;
import org.example.models.Player;
import org.example.models.Room;
import org.example.protocol.Message;
import org.example.protocol.MessageTypes;
import org.example.server.Connection;

import java.io.IOException;

public class StartGameListener extends AbstractServerEventListener {

    @Override
    public void handle(Connection connection, Message message) throws IOException {

        int roomID;
        try {
            roomID = (Integer) Message.deserialize(message.getData());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (Room r : server.getRooms()) {
            if (r.getId() == roomID) {
                Message messageS = new Message();
                messageS.setType(MessageTypes.START_GAME);
                messageS.setData("game started".getBytes());
                try {
                    server.sendBroadCastMessage(messageS, r);
                } catch (ServerException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
