package org.example;

public class CantBookException extends Exception {
    public CantBookException(String message) {
        super(message);
    }

    public CantBookException() {
        super("Cannot book the slot");
    }
}
