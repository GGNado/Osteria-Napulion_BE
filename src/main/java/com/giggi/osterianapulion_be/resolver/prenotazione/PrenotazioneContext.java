package com.giggi.osterianapulion_be.resolver.prenotazione;

import com.giggi.osterianapulion_be.entity.Prenotazione;
import com.giggi.osterianapulion_be.entity.Tavolo;
import lombok.Value;

import java.util.List;

@Value
public class PrenotazioneContext {
    List<Tavolo> tavoliCompatibili;
    List<Prenotazione> prenotazioniEsistenti;
    Prenotazione prenotazione;
}
