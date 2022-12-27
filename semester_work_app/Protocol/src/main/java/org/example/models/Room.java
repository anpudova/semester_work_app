package org.example.models;

import javax.imageio.ImageIO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Room implements Serializable {

    private Integer id;

    private static int countRooms = 0;
    private ArrayList<Player> players;
    private Integer currentRound;
    private final Integer MAX_NUM_OF_PLAYERS = 10;

    private ArrayList<ArrayList<HashMap<Player, String>>> textForDrawingInGame;
    private ArrayList<ArrayList<HashMap<Player, ImageIO>>> ImageForDrawingInGame;

    public Room() {
        id = countRooms++;
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

    public Integer getNumber() {
        return 0;
    }

    public Integer getId() {
        return id;
    }

    public ArrayList<ArrayList<HashMap<Player, String>>> getTextForDrawingInGame() {
        return textForDrawingInGame;
    }

    public ArrayList<ArrayList<HashMap<Player, ImageIO>>> getImageForDrawingInGame() {
        return ImageForDrawingInGame;
    }

    public Integer getCurrentRound() {
        return currentRound;
    }
    public void setCurrentRound(Integer currentRound) {
        this.currentRound = currentRound;
    }
}
