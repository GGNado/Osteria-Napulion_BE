package com.giggi.osterianapulion_be.service;

import java.util.List;

import com.giggi.osterianapulion_be.entity.BloccaPrenotazione;

public interface BloccaPrenotazioneService {
    BloccaPrenotazione save(BloccaPrenotazione bloccaPrenotazione);

    BloccaPrenotazione update(BloccaPrenotazione bloccaPrenotazione);

    void deleteById(Long id);

    List<BloccaPrenotazione> findAll();

    BloccaPrenotazione findById(Long id);
}