package org.example.server;

import org.example.models.Player;
import org.example.protocol.MessageInputStream;
import org.example.protocol.MessageOutputStream;

import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread{

    protected int id = 1;
    protected Socket socket;
    protected MessageInputStream in;
    protected MessageOutputStream out;
    protected Player player;

    public Connection(Socket socket) throws IOException {
        this.id += 1;
        this.socket = socket;
        this.in = new MessageInputStream(socket.getInputStream());
        this.out = new MessageOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {

    }
}
