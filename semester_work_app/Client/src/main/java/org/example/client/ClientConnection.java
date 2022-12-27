package org.example.client;

import org.example.exceptions.ClientException;
import org.example.models.Player;
import org.example.protocol.Message;
import org.example.protocol.MessageInputStream;
import org.example.protocol.MessageOutputStream;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientConnection {

    private MessageInputStream in;
    private MessageOutputStream out;
    private Socket socket;
    private Player player;

    public ClientConnection(InetAddress inetAddress, int port) throws ClientException {
        try {
            this.socket = new Socket(inetAddress, port);
            this.in = new MessageInputStream(socket.getInputStream());
            this.out = new MessageOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new ClientException("Connection problem.");
        }
    }

    public void sendMessage(Message message) throws ClientException {
        try {
            out.writeMessage(message);
            out.flush();
        } catch (IOException e) {
            throw new ClientException("The message was not sent.");
        }
    }

    public Message acceptMessage() {
        try {
            return in.readMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public MessageInputStream getIn() {
        return in;
    }

    public MessageOutputStream getOut() {
        return out;
    }
}
