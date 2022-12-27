package org.example.protocol;

import java.lang.reflect.Field;
import java.util.Arrays;

public class MessageTypes {
    public static final byte PLAYER_CONNECT = 1;
    public static final byte CONNECTION_SUCCESSFUL = 2;
    public static final byte LIST_ROOMS = 3;
    public static final byte CREATE_ROOM = 4;
    public static final byte JOIN_ROOM = 5;
    public static final byte JOIN_ROOM_SUCCESSFUL = 6;
    public static final byte LIST_PLAYERS_IN_ROOM = 7;
    public static final byte MASTER_START_GAME = 8;
    public static final byte START_GAME = 9;


    public static byte[] getTypes() {
        byte[] types = new byte[100];
        Field[] fields = MessageTypes.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                types[i] = (byte) fields[i].get(null);
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }
        return types;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getTypes()));
    }
}
