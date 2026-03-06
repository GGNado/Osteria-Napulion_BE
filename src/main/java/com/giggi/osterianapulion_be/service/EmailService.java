package com.giggi.osterianapulion_be.service;

import com.giggi.osterianapulion_be.entity.Prenotazione;
import org.springframework.scheduling.annotation.Async;

public interface EmailService {
    @Async
    void sendConfermaPrenotazioneAsync(String destinatario, Prenotazione res);
}
