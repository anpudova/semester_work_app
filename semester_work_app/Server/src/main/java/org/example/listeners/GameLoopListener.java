package org.example.listeners;

import org.example.exceptions.ServerException;
import org.example.models.Player;
import org.example.models.Room;
import org.example.protocol.Message;
import org.example.protocol.MessageTypes;
import org.example.server.Connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GameLoopListener extends AbstractServerEventListener {

    @Override
    public void handle(Connection connection, Message message) throws IOException {

        int roomID;
        try {
            roomID = (Integer) Message.deserialize(message.getData());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Room room = null;
        for (Room r : server.getRooms()) {
            if (r.getId() == roomID) {
                room = r;
            }
        }

        while (room.getCurrentRound() != 4) {

            ArrayList<HashMap<Player, String>> textInRound = new ArrayList<>();
            for (Player p : room.getAllPlayers()) {
                String textForDrawing = null;
                try {
                    textForDrawing = (String) Message.deserialize(message.getData());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                textInRound.add((HashMap<Player, String>) new HashMap<>().put(p, textForDrawing));
            }
            room.getTextForDrawingInGame().add(textInRound);

        }


    }

}
