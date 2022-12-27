package org.example.server;

import org.example.listeners.AbstractServerEventListener;
import org.example.listeners.ServerEventListener;
import org.example.models.Player;
import org.example.protocol.Message;
import org.example.protocol.MessageInputStream;
import org.example.protocol.MessageOutputStream;

import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread{

    protected int id = 1;
    protected Socket socket;
    protected Server server;
    protected MessageInputStream in;
    protected MessageOutputStream out;
    protected Player player;

    public Connection(Server server, Socket socket) throws IOException {
        this.id += 1;
        this.socket = socket;
        this.server = server;
        this.in = new MessageInputStream(socket.getInputStream());
        this.out = new MessageOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        Message message;

        try {
            while ((message = in.readMessage()) != null) {
                System.out.println(message);
                ServerEventListener listener = AbstractServerEventListener.getListener(message.getType());
                System.out.println(message.getType());
                listener.init(server);

                listener.handle(this, message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public long getId() {
        return id;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
