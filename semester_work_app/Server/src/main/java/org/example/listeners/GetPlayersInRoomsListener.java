package org.example.listeners;

import org.example.exceptions.ServerException;
import org.example.models.Room;
import org.example.protocol.Message;
import org.example.protocol.MessageTypes;
import org.example.server.Connection;

import java.io.IOException;

public class GetPlayersInRoomsListener extends AbstractServerEventListener {

    @Override
    public void handle(Connection connection, Message message) throws IOException {
        int roomID;
        try {
            roomID = (Integer) Message.deserialize(message.getData());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Room room = null;
        for (Room r : server.getRooms()) {
            if (r.getId() == roomID) {
                room = r;
            }
        }

        Message messageS = new Message();
        messageS.setType(MessageTypes.LIST_PLAYERS_IN_ROOM);
        messageS.setData(Message.serialize(room));

        try {
            server.sendMessage(connection, messageS);
        } catch (ServerException e) {
            throw new RuntimeException(e);
        }

    }
}
