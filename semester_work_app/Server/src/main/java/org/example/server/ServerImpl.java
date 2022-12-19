package org.example.server;

import org.example.exceptions.ServerException;
import org.example.listeners.ServerEventListener;
import org.example.models.Room;
import org.example.protocol.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerImpl implements Server{

    protected int port;
    protected ServerSocket serverSocket;
    protected boolean started;
    protected List<Connection> connections;
    protected List<ServerEventListener> eventListeners;

    public ServerImpl(int port) {
        this.port = port;
        this.started = false;
        this.eventListeners = new ArrayList<>();
    }

    @Override
    public void start() throws ServerException {
        try {
            serverSocket = new ServerSocket(port);
            started = true;

            while(true) {
                Socket socket = serverSocket.accept();
                handleConnect(socket);
            }
        } catch (IOException e) {
            throw new ServerException("");
        }

    }

    private void handleConnect(Socket socket) throws ServerException {
        Connection connection;
        try {
            connection = new Connection(socket);
        } catch (IOException e) {
            throw new ServerException("Connection problem.");
        }
        connections.add(connection);
        connection.start();
    }

    @Override
    public void registerListener(ServerEventListener listener) throws ServerException {
        if (started) {
            throw new ServerException("The server is already running.");
        }
        listener.init(this);
        eventListeners.add(listener);
    }

    @Override
    public void sendMessage(Connection connection, Message message) throws ServerException {
        if (!started) {
            throw new ServerException("The server is not running yet.");
        }
        try {
            connection.out.writeMessage(message);
            connection.out.flush();
        } catch (IOException e) {
            throw new ServerException("The message was not sent.");
        }
    }

    @Override
    public void sendBroadCastMessage(Message message, Room room) throws ServerException {
        if (!started) {
            throw new ServerException("The server is not running yet.");
        }
        try {
        for (Connection connect: connections) {
            if (room.getAllPlayers().contains(connect.player)) {
                connect.out.writeMessage(message);
                connect.out.flush();
            }
        }
        } catch (IOException e) {
            throw new ServerException("The message was not sent.");
        }
    }
}
