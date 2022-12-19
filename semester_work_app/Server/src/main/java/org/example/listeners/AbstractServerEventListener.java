package org.example.listeners;

import org.example.server.ServerImpl;

public abstract class AbstractServerEventListener implements ServerEventListener {

    protected boolean init;
    protected ServerImpl server;
    protected byte type;

    @Override
    public void init(ServerImpl server) {
        this.init = true;
        this.server = server;
    }

    @Override
    public int getType() {
        return type;
    }

    public void getListener(byte type) {
        switch(type) {

        }
    }
}
