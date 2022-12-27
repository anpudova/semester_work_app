package org.example.exceptions;

public class IllegalTypeMessageException extends IllegalArgumentException{

    public IllegalTypeMessageException() {
        super();
    }

    public IllegalTypeMessageException(String s) {
        super(s);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
