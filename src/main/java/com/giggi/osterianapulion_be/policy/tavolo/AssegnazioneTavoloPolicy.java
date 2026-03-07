package com.giggi.osterianapulion_be.policy.tavolo;

import com.giggi.osterianapulion_be.entity.Tavolo;
import com.giggi.osterianapulion_be.resolver.prenotazione.PrenotazioneContext;

public interface AssegnazioneTavoloPolicy {
    Tavolo assegnaTavolo(PrenotazioneContext context);
}

