package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private ArrayList<Player> players;
    private Integer currentRound;
    private final Integer MAX_NUM_OF_PLAYERS = 10;

    public Room() {
        this.players = new ArrayList<>();
        this.currentRound = 1;
    }

    public List<Player> getAllPlayers() {
        return players;
    }

    public Integer getCountPlayers() {
        return players.size();
    }

    public boolean addPlayer(Player player) {
        if (players.size() < MAX_NUM_OF_PLAYERS) {
            players.add(player);
            return true;
        } else {
            return false;
        }
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }


}
