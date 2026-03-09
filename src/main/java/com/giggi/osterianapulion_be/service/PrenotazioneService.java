package com.giggi.osterianapulion_be.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import com.giggi.osterianapulion_be.dto.response.prenotazione.DataCounterDTO;
import com.giggi.osterianapulion_be.entity.Prenotazione;
import com.giggi.osterianapulion_be.entity.StatoPrenotazione;

public interface PrenotazioneService {
    Prenotazione save(Prenotazione prenotazione);

    Prenotazione update(Prenotazione prenotazione);

    void deleteById(Long id);

    List<Prenotazione> findAll();

    Prenotazione findById(Long id);

    List<Prenotazione> findAllFromDate(LocalDate date);

    Prenotazione setStato(StatoPrenotazione statoPrenotazione, Long idPrenotazione);

    DataCounterDTO countPrenotazioniByMonth();

    Prenotazione resendEmail(Prenotazione prenotazione);
}