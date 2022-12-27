package org.example.listeners;

import org.example.models.Room;
import org.example.protocol.Message;
import org.example.protocol.MessageTypes;
import org.example.server.Connection;

import java.io.IOException;
import java.util.List;

public class CreateRoomListener extends AbstractServerEventListener {

    @Override
    public void handle(Connection connection, Message message) {

        try {
            server.getRooms().add((Room) message.deserialize(message.getData()));

            new GetRoomsListener().handle(connection, message);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
