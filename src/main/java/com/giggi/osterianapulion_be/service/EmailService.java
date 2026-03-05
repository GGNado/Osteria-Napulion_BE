package com.giggi.osterianapulion_be.service;

import com.giggi.osterianapulion_be.entity.Prenotazione;

public interface EmailService {
    void sendConfermaPrenotazione(String destinatario, Prenotazione res);
}
