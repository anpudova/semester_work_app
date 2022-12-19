package org.example.protocol;

import java.io.IOException;
import java.io.OutputStream;

public class MessageOutputStream extends OutputStream{

    private OutputStream out;
    public static final byte[] VERSION_PROTOCOL = new byte[]{0x0, 0x1};

    public MessageOutputStream(OutputStream out) {
        this.out = out;
    }

    public void writeMessage(Message message) throws IOException {
        out.write(VERSION_PROTOCOL);
        out.write(message.getType());

        byte[] dataMessage = message.getData();
        out.write((byte) (dataMessage.length >> 8));
        out.write((byte) dataMessage.length);

        for (byte data : dataMessage) {
            out.write(data);
        }
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }
}
