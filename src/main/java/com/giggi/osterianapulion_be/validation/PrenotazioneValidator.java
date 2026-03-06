package com.giggi.osterianapulion_be.validation;

import com.giggi.osterianapulion_be.entity.Prenotazione;
import com.giggi.osterianapulion_be.exception.prenotazione.ReservationUnavailableException;
import com.giggi.osterianapulion_be.repository.PrenotazioneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.regex.Pattern;

@Slf4j
@Component
@RequiredArgsConstructor
public class PrenotazioneValidator {

    private static final LocalTime ORARIO_APERTURA = LocalTime.of(19, 0);
    private static final LocalTime ORARIO_CHIUSURA = LocalTime.of(23, 0);
    private static final int MINUTI_ANTICIPO = 5;
    private static final int COPERTI_MIN = 1;
    private static final int COPERTI_MAX = 10;

    private static final Pattern TELEFONO_PATTERN = Pattern.compile("^\\+?[0-9]{8,15}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private final PrenotazioneRepository prenotazioneRepository;

    public void validate(Prenotazione p) {
        log.info("Validazione prenotazione: {}", p);

        validateTelefono(p.getTelefonoCliente());
        validateEmail(p.getEmailCliente());
        validateOrario(p.getDataOra());
        validateAnticipoMinimo(p.getDataOra());
        validateCoperti(p.getCoperti());
        validatePrenotazioneDuplicataPerTelefono(p);

        log.info("Prenotazione validata con successo.");
    }

    private void validateTelefono(String telefono) {
        log.info("Validazione numero di telefono...");

        if (telefono == null || telefono.isBlank()) {
            throw new ReservationUnavailableException("Il numero di telefono è obbligatorio.");
        }

        String telefonoNormalizzato = telefono.replaceAll("\\s+", "");

        if (!TELEFONO_PATTERN.matcher(telefonoNormalizzato).matches()) {
            throw new ReservationUnavailableException("Numero di telefono non valido.");
        }
    }

    private void validateEmail(String email) {
        log.info("Validazione email...");

        if (email == null || email.isBlank()) {
            throw new ReservationUnavailableException("L'email è obbligatoria.");
        }

        String emailNormalizzata = email.trim();

        if (!EMAIL_PATTERN.matcher(emailNormalizzata).matches()) {
            throw new ReservationUnavailableException("Email non valida.");
        }
    }

    private void validateOrario(LocalDateTime dataOra) {
        log.info("Validazione orario prenotabile (19:00 - 23:00)...");

        LocalTime ora = dataOra.toLocalTime();
        if (ora.isBefore(ORARIO_APERTURA) || ora.isAfter(ORARIO_CHIUSURA)) {
            throw new ReservationUnavailableException("Orario non prenotabile (19:00-23:00).");
        }
    }

    private void validateAnticipoMinimo(LocalDateTime dataOra) {
        log.info("Validazione anticipo minimo di {} minuti...", MINUTI_ANTICIPO);

        if (dataOra.isBefore(LocalDateTime.now().plusMinutes(MINUTI_ANTICIPO))) {
            throw new ReservationUnavailableException("Data/Ora non valida (minimo 5 minuti di anticipo).");
        }
    }

    private void validateCoperti(int coperti) {
        log.info("Validazione coperti ({}-{})...", COPERTI_MIN, COPERTI_MAX);

        if (coperti < COPERTI_MIN || coperti > COPERTI_MAX) {
            throw new ReservationUnavailableException("Numero di coperti non valido (1-10).");
        }
    }

    private void validatePrenotazioneDuplicataPerTelefono(Prenotazione p) {
        log.info("Controllo prenotazione esistente per numero di telefono nella stessa data...");

        LocalDateTime inizioGiorno = p.getDataOra().toLocalDate().atStartOfDay();
        LocalDateTime fineGiorno = inizioGiorno.plusDays(1);

        boolean haGiaPrenotato = prenotazioneRepository.existsBytelefonoClienteAndDataOraBetween(
                p.getTelefonoCliente(),
                inizioGiorno,
                fineGiorno
        );

        if (haGiaPrenotato) {
            throw new ReservationUnavailableException(
                    "Esiste già una prenotazione per questo numero di telefono in data " +
                            p.getDataOra().toLocalDate()
            );
        }
    }
}
