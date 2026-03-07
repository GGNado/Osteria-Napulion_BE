package com.giggi.osterianapulion_be.exception.prenotazione;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(String message) {
        super(message);
    }
}
