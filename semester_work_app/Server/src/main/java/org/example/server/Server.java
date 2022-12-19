package org.example.server;

import org.example.exceptions.ServerException;
import org.example.listeners.ServerEventListener;
import org.example.models.Room;
import org.example.protocol.Message;

public interface Server {
    public void start() throws ServerException;
    public void registerListener(ServerEventListener listener) throws ServerException;
    public void sendMessage(Connection connection, Message message) throws ServerException;
    public void sendBroadCastMessage(Message message, Room room) throws ServerException;

}
