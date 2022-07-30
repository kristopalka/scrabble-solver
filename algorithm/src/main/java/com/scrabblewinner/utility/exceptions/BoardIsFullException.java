package com.scrabblewinner.utility.exceptions;

public class BoardIsFullException extends RuntimeException {
    public BoardIsFullException(String message) {
        super(message);
    }
}
