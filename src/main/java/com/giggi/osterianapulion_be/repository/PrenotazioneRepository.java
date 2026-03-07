package com.giggi.osterianapulion_be.repository;

import com.giggi.osterianapulion_be.entity.StatoPrenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import com.giggi.osterianapulion_be.entity.Prenotazione;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByDataOraBetween(LocalDateTime data, LocalDateTime data2);
    List<Prenotazione> findByDataOraBetweenAndStato(LocalDateTime data, LocalDateTime data2, StatoPrenotazione stato);

    boolean existsBytelefonoClienteAndDataOraBetween(String telefonoCliente, LocalDateTime data, LocalDateTime data2);

    Long id(Long id);

    List<Prenotazione> findByDataOraBetweenAndTavoloIsNotNull(LocalDateTime localDateTime, LocalDateTime localDateTime1);
}