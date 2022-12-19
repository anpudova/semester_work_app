package org.example.protocol;

import java.lang.reflect.Field;

public class MessageTypes {

    public static final byte type1 = 1;
    public static final byte type2 = 2;
    public static final byte type3 = 3;

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
}
