package com.giggi.osterianapulion_be.service.impl;

import com.giggi.osterianapulion_be.dto.response.prenotazione.DataCounterDTO;
import com.giggi.osterianapulion_be.entity.StatoPrenotazione;
import com.giggi.osterianapulion_be.entity.Tavolo;
import com.giggi.osterianapulion_be.exception.prenotazione.ReservationNotFoundException;
import com.giggi.osterianapulion_be.policy.prenotazione.HandlePrenotazione;
import com.giggi.osterianapulion_be.policy.prenotazione.HandlerPrenotazionePolicy;
import com.giggi.osterianapulion_be.policy.tavolo.AssegnazioneTavoloPolicy;
import com.giggi.osterianapulion_be.resolver.prenotazione.PrenotazioneContext;
import com.giggi.osterianapulion_be.resolver.prenotazione.PrenotazioneResolver;
import com.giggi.osterianapulion_be.validation.PrenotazioneValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.giggi.osterianapulion_be.entity.Prenotazione;
import com.giggi.osterianapulion_be.repository.PrenotazioneRepository;
import com.giggi.osterianapulion_be.service.PrenotazioneService;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PrenotazioneServiceImpl implements PrenotazioneService {
    private final PrenotazioneValidator prenotazioneValidator;
    private final PrenotazioneRepository prenotazioneRepository;
    private final PrenotazioneResolver prenotazioneResolver;
    private final HandlePrenotazione handlePrenotazione;
    private final AssegnazioneTavoloPolicy policy;
    private final EmailServiceImpl emailService;

    @Override
    public Prenotazione save(Prenotazione prenotazione) {
        StopWatch sw = new StopWatch();
        sw.start();

        prenotazioneValidator.validate(prenotazione);
        PrenotazioneContext context = prenotazioneResolver.resolve(prenotazione);
        Tavolo tavolo = policy.assegnaTavolo(context);
        prenotazione.setTavolo(tavolo);
        prenotazione.setStato(StatoPrenotazione.IN_ATTESA);
        Prenotazione p = prenotazioneRepository.save(prenotazione);
        emailService.sendRicezionePrenotazioneAsync(p.getEmailCliente(), prenotazione);

        sw.stop();
        log.info("Prenotazione salvata in {}ms", sw.getTime());

        return p;
    }

    @Override
    public Prenotazione update(Prenotazione prenotazione) {
        return prenotazioneRepository.save(prenotazione);
    }

    @Override
    public void deleteById(Long id) {
        prenotazioneRepository.deleteById(id);
    }

    @Override
    public List<Prenotazione> findAll() {
        return prenotazioneRepository.findAll();
    }

    @Override
    public Prenotazione findById(Long id) {
        return prenotazioneRepository.findById(id).orElse(null);
    }

    @Override
    public List<Prenotazione> findAllFromDate(LocalDate date) {
        return prenotazioneRepository
                .findByDataOraBetween(
                        date.atTime(0, 0),
                        date.atTime(23, 59)
                );
    }

    @Override
    public Prenotazione setStato(StatoPrenotazione statoPrenotazione, Long idPrenotazione) {
        Prenotazione prenotazione = prenotazioneRepository.findById(idPrenotazione).orElseThrow(
                () -> new ReservationNotFoundException("Prenotazione non trovata con id " + idPrenotazione)
        );
        prenotazioneValidator.validate(prenotazione, statoPrenotazione);
        prenotazione.setStato(statoPrenotazione);
        handlePrenotazione.handleStateChangeEvent(prenotazione);

        return prenotazioneRepository.save(prenotazione);
    }

    @Override
    public DataCounterDTO countPrenotazioniByMonth() {
        LocalDate now = LocalDate.now();
        LocalDateTime inizio = now.withDayOfMonth(1).atStartOfDay();
        LocalDateTime fine = now.withDayOfMonth(now.lengthOfMonth()).atTime(23, 59, 59);

        Map<LocalDate, Integer> counter = prenotazioneRepository
                .countByGiorno(inizio, fine)
                .stream()
                .collect(Collectors.toMap(
                        row -> (LocalDate) row[0],
                        row -> ((Long) row[1]).intValue()
                ));

        // Riempie i giorni mancanti con 0
        now.withDayOfMonth(1).datesUntil(fine.toLocalDate().plusDays(1))
                .forEach(date -> counter.putIfAbsent(date, 0));

        return new DataCounterDTO(counter);
    }

    @Override
    public Prenotazione resendEmail(Prenotazione prenotazione) {
        return null;
    }
}