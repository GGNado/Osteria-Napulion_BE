package com.giggi.osterianapulion_be.policy.prenotazione;

import com.giggi.osterianapulion_be.entity.Prenotazione;

public interface HandlePrenotazione {
    void handleStateChangeEvent(Prenotazione prenotazione);
}
