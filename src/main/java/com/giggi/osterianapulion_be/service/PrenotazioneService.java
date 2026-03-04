package com.giggi.osterianapulion_be.service;

import java.util.List;

import com.giggi.osterianapulion_be.entity.Prenotazione;

public interface PrenotazioneService {
    Prenotazione save(Prenotazione prenotazione);

    Prenotazione update(Prenotazione prenotazione);

    void deleteById(Long id);

    List<Prenotazione> findAll();

    Prenotazione findById(Long id);
}