package com.giggi.osterianapulion_be.service.impl;

import com.giggi.osterianapulion_be.entity.Tavolo;
import com.giggi.osterianapulion_be.mapper.PrenotazioneMapper;
import com.giggi.osterianapulion_be.policy.AssegnazioneTavoloPolicy;
import com.giggi.osterianapulion_be.resolver.prenotazione.PrenotazioneContext;
import com.giggi.osterianapulion_be.resolver.prenotazione.PrenotazioneResolver;
import com.giggi.osterianapulion_be.validation.PrenotazioneValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.giggi.osterianapulion_be.entity.Prenotazione;
import com.giggi.osterianapulion_be.repository.PrenotazioneRepository;
import com.giggi.osterianapulion_be.service.PrenotazioneService;

@Service
@Transactional
@RequiredArgsConstructor
public class PrenotazioneServiceImpl implements PrenotazioneService {
    private final PrenotazioneValidator prenotazioneValidator;
    private final PrenotazioneRepository prenotazioneRepository;
    private final PrenotazioneResolver prenotazioneResolver;
    private final AssegnazioneTavoloPolicy policy;
    private final PrenotazioneMapper mapper;

    @Override
    public Prenotazione save(Prenotazione prenotazione) {
        prenotazioneValidator.validate(prenotazione);
        PrenotazioneContext context = prenotazioneResolver.resolve(prenotazione);
        Tavolo tavolo = policy.assegnaTavolo(context);
        prenotazione.setTavolo(tavolo);
        return prenotazioneRepository.save(prenotazione);
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