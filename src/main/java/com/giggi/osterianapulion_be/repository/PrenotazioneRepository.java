package com.giggi.osterianapulion_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.giggi.osterianapulion_be.entity.Prenotazione;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
}