package org.example.app;

import org.example.exceptions.ServerException;
import org.example.server.Server;
import org.example.server.ServerImpl;

public class Main {
    public static void main(String[] args) throws ServerException {
        Server server = new ServerImpl(6666);
        try {
            server.start();
        } catch (ServerException e) {
            throw new ServerException("Problem");
        }
    }
}