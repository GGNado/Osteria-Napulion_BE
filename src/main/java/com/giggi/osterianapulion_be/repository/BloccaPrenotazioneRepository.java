package com.giggi.osterianapulion_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.giggi.osterianapulion_be.entity.BloccaPrenotazione;
import org.springframework.stereotype.Repository;

@Repository
public interface BloccaPrenotazioneRepository extends JpaRepository<BloccaPrenotazione, Long> {
}