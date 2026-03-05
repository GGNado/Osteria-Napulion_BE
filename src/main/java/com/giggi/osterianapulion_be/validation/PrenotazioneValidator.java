package com.giggi.osterianapulion_be.validation;

import com.giggi.osterianapulion_be.entity.Prenotazione;
import com.giggi.osterianapulion_be.exception.prenotazione.ReservationUnavailableException;
import com.giggi.osterianapulion_be.repository.PrenotazioneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class PrenotazioneValidator {
    private final PrenotazioneRepository prenotazioneRepository;

    public void validate(Prenotazione p) {

        log.info("Validazione prenotazione: {}", p);
        log.info("Validazione Orario 19:00 - 23:00.....");

        LocalTime ora = p.getDataOra().toLocalTime();
        if (ora.isBefore(LocalTime.of(19,0)) || ora.isAfter(LocalTime.of(23,0))) {
            throw new ReservationUnavailableException("Orario non prenotabile (19:00-23:00).");
        }

        log.info("Orario Validato.");
        log.info("Validazione Coperti 1-10.....");

        if (p.getCoperti() < 1 || p.getCoperti() > 10) {
            throw new ReservationUnavailableException("Numero di coperti non valido (1-10).");
        }

        log.info("Controllo se numero di telefono già prenotato.");

        LocalDateTime inizioGiorno = p.getDataOra().toLocalDate().atStartOfDay();
        LocalDateTime fineGiorno = inizioGiorno.plusDays(1);

        boolean haGiaPrenotato = prenotazioneRepository
                .existsBytelefonoClienteAndDataOraBetween(
                        p.getTelefonoCliente(),
                        inizioGiorno,
                        fineGiorno
                );

        if (haGiaPrenotato)
            throw new ReservationUnavailableException(
                    "Esiste già una prenotazione per questo numero di telefono in data " +
                            p.getDataOra().toLocalDate()
            );

        log.info("Coperti Validati.");

    }
}
