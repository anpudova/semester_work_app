package org.example.server;

import org.example.exceptions.ServerException;
import org.example.listeners.ServerEventListener;
import org.example.models.Room;
import org.example.protocol.Message;

import java.util.List;

public interface Server {
    public void start() throws ServerException;
    public void registerListener(ServerEventListener listener) throws ServerException;
    public void sendMessage(Connection connection, Message message) throws ServerException;
    public void sendBroadCastMessage(Message message, Room room) throws ServerException;

    public void sendMessageAllConnections(Message message);

    public List<Room> getRooms();

}
