package com.scrabble.backend.algorithm.utility.exceptions;

public class BoardIsFullException extends RuntimeException {
    public BoardIsFullException(String message) {
        super(message);
    }
}
