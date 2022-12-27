package org.example.listeners;

import org.example.protocol.Message;
import org.example.protocol.MessageTypes;
import org.example.server.Connection;

import java.io.IOException;

public class GetRoomsListener extends AbstractServerEventListener {

    @Override
    public void handle(Connection connection, Message message) throws IOException {

        Message messageSendAllRooms = new Message();
        messageSendAllRooms.setType(MessageTypes.LIST_ROOMS);
        messageSendAllRooms.setData(Message.serialize(server.getRooms()));
        server.sendMessageAllConnections(messageSendAllRooms);

    }

}
