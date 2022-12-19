package org.example.protocol;

import java.util.Arrays;

public class Message {

    private byte[] data;
    private byte type;

    public Message(byte[] data, byte type) {
        this.data = data;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Message{" +
                "data=" + Arrays.toString(data) +
                ", type=" + type +
                '}';
    }

    public byte[] getData() {
        return data;
    }

    public byte getType() {
        return type;
    }
}
