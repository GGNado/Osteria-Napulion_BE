package com.giggi.osterianapulion_be.policy;

import com.giggi.osterianapulion_be.entity.Prenotazione;
import com.giggi.osterianapulion_be.entity.Tavolo;
import com.giggi.osterianapulion_be.repository.PrenotazioneRepository;
import com.giggi.osterianapulion_be.repository.TavoloRepository;
import com.giggi.osterianapulion_be.resolver.prenotazione.PrenotazioneContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

public interface AssegnazioneTavoloPolicy {
    Tavolo assegnaTavolo(PrenotazioneContext context);
}

