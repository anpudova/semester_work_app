package org.example.protocol;

import java.io.*;
import java.util.Arrays;

public class Message {

    private byte[] data;
    private byte type;

    public Message() {

    }

    public Message(byte type, byte[] data) {
        this.type = type;
        this.data = data;
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

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public static byte[] serialize(Object object) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
        objectStream.writeObject(object);
        return byteStream.toByteArray();
    }

    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectStream = new ObjectInputStream(byteStream);
        return objectStream.readObject();
    }


}
