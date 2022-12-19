package org.example.listeners;

import org.example.protocol.Message;
import org.example.server.ServerImpl;

public interface ServerEventListener {
    public void init(ServerImpl server);
    public void handle(int connectionId, Message message);
    public int getType();
}
