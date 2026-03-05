package com.giggi.osterianapulion_be.policy;

import com.giggi.osterianapulion_be.entity.Tavolo;
import com.giggi.osterianapulion_be.exception.prenotazione.ReservationUnavailableException;
import com.giggi.osterianapulion_be.resolver.prenotazione.PrenotazioneContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AssegnazioneTavoloSmartPolicy implements AssegnazioneTavoloPolicy{

    @Override
    public Tavolo assegnaTavolo(PrenotazioneContext context) {

        Set<Long> tavoliOccupati = context.getPrenotazioniEsistenti()
                .stream()
                .map(p -> p.getTavolo().getId())
                .collect(Collectors.toSet());

        // Sceglie il tavolo libero con meno posti (meno spreco)
        return context.getTavoliCompatibili()
                .stream()
                .filter(t -> !tavoliOccupati.contains(t.getId()))
                .min(Comparator.comparingInt(Tavolo::getMassimoPosti))
                .orElseThrow(() -> new ReservationUnavailableException("Non ci sono tavoli disponibili per la prenotazione richiesta"));
    }
}
