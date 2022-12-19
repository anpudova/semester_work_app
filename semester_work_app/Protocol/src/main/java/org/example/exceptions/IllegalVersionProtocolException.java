package org.example.exceptions;

public class IllegalVersionProtocolException extends IllegalArgumentException {

    public IllegalVersionProtocolException() {
        super();
    }

    public IllegalVersionProtocolException(String s) {
        super(s);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
