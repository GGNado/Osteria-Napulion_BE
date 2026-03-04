package com.giggi.osterianapulion_be.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.giggi.osterianapulion_be.entity.BloccaPrenotazione;
import com.giggi.osterianapulion_be.repository.BloccaPrenotazioneRepository;
import com.giggi.osterianapulion_be.service.BloccaPrenotazioneService;

@Service
@Transactional
@RequiredArgsConstructor
public class BloccaPrenotazioneServiceImpl implements BloccaPrenotazioneService {

    private final BloccaPrenotazioneRepository bloccaPrenotazioneRepository;

    @Override
    public BloccaPrenotazione save(BloccaPrenotazione bloccaPrenotazione) {
        return bloccaPrenotazioneRepository.save(bloccaPrenotazione);
    }

    @Override
    public BloccaPrenotazione update(BloccaPrenotazione bloccaPrenotazione) {
        return bloccaPrenotazioneRepository.save(bloccaPrenotazione);
    }

    @Override
    public void deleteById(Long id) {
        bloccaPrenotazioneRepository.deleteById(id);
    }

    @Override
    public List<BloccaPrenotazione> findAll() {
        return bloccaPrenotazioneRepository.findAll();
    }

    @Override
    public BloccaPrenotazione findById(Long id) {
        return bloccaPrenotazioneRepository.findById(id).orElse(null);
    }
}