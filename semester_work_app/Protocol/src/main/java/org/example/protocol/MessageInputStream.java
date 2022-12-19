package org.example.protocol;

import org.example.exceptions.IllegalTypeMessageException;
import org.example.exceptions.IllegalVersionProtocolException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class MessageInputStream extends InputStream {

    private InputStream in;
    public static final byte[] VERSION_PROTOCOL = new byte[]{0x0, 0x1};

    public MessageInputStream(InputStream in) {
        this.in = in;
    }

    public Message readMessage() throws IOException, IllegalVersionProtocolException, IllegalTypeMessageException {
        int firstByte = in.read();
        if (firstByte == -1) {
            return null;
        }
        int secondByte = in.read();
        byte[] startBytes = new byte[] {(byte) firstByte, (byte) secondByte};
        if (!Arrays.equals(startBytes, VERSION_PROTOCOL)) {
            throw new IllegalVersionProtocolException("Invalid ru.kpfu.itis.protocol version.");
        }
        byte typeMessage = (byte) in.read();
        if (!Arrays.asList(MessageTypes.getTypes()).contains(typeMessage)) {
            throw new IllegalTypeMessageException("Invalid message type");
        }
        int size = in.read() << 8 | in.read();
        byte[] dataMessage = new byte[size];
        for (int i = 0; i < size; i++) {
            dataMessage[i] = (byte) in.read();
        }

        return new Message(dataMessage, typeMessage);
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }
}
