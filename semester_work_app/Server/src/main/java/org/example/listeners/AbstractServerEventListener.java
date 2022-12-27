package org.example.listeners;

import org.example.server.Server;
import org.example.server.ServerImpl;

import static org.example.protocol.MessageTypes.*;

public abstract class AbstractServerEventListener implements ServerEventListener {

    protected boolean init;
    protected Server server;
    protected byte type;

    @Override
    public void init(Server server) {
        this.init = true;
        this.server = server;
    }

    @Override
    public int getType() {
        return type;
    }

    public static ServerEventListener getListener(byte type) {
        switch(type) {
            case PLAYER_CONNECT:
                return new ConnectClientListener();
            case CREATE_ROOM:
                return new CreateRoomListener();
            case LIST_ROOMS:
                return new GetRoomsListener();
            case JOIN_ROOM:
                return new JoinRoomListener();
            case LIST_PLAYERS_IN_ROOM:
                return new GetPlayersInRoomsListener();
            case MASTER_START_GAME:
                return new StartGameListener();

            default: throw new IllegalArgumentException("Illegal listener");
        }
    }
}
