package org.example.listeners;

import org.example.protocol.Message;
import org.example.server.Connection;
import org.example.server.Server;
import org.example.server.ServerImpl;

import java.io.IOException;

public interface ServerEventListener {
    public void init(Server server);
    public void handle(Connection connection, Message message) throws IOException;
    public int getType();
}
