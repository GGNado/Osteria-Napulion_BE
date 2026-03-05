package com.giggi.osterianapulion_be.exception.tavolo;

public class TableUnavailableException extends RuntimeException {
    public TableUnavailableException(String message) {
        super(message);
    }
}
