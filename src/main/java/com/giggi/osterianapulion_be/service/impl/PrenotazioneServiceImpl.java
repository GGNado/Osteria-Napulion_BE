package com.giggi.osterianapulion_be.service.impl;

import com.giggi.osterianapulion_be.entity.Tavolo;
import com.giggi.osterianapulion_be.policy.AssegnazioneTavoloPolicy;
import com.giggi.osterianapulion_be.resolver.prenotazione.PrenotazioneContext;
import com.giggi.osterianapulion_be.resolver.prenotazione.PrenotazioneResolver;
import com.giggi.osterianapulion_be.validation.PrenotazioneValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        Prenotazione p = prenotazioneRepository.save(prenotazione);
        emailService.sendConfermaPrenotazioneAsync(p.getEmailCliente(), prenotazione);

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
}