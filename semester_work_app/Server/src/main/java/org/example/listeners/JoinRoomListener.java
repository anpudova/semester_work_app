package org.example.listeners;

import org.example.exceptions.ServerException;
import org.example.models.Room;
import org.example.protocol.Message;
import org.example.protocol.MessageTypes;
import org.example.server.Connection;

import java.io.IOException;

public class JoinRoomListener extends AbstractServerEventListener{

    @Override
    public void handle(Connection connection, Message message) throws IOException {
        int roomID;
        try {
            roomID = (Integer) Message.deserialize(message.getData());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (Room room : server.getRooms()) {
            if (room.getId() == roomID) {
                room.addPlayer(connection.getPlayer());
            }
        }

        Message messageS = new Message();
        messageS.setType(MessageTypes.JOIN_ROOM_SUCCESSFUL);
        messageS.setData("successful".getBytes());

        try {
            server.sendMessage(connection, messageS);
        } catch (ServerException e) {
            throw new RuntimeException(e);
        }

    }

}
