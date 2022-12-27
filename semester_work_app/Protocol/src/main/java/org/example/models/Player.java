package org.example.models;

import java.io.Serializable;
import java.util.Objects;

public class Player implements Serializable {

    private Integer id;
    private String name;

    public Player(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
