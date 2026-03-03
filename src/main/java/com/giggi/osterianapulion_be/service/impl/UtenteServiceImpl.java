package com.giggi.osterianapulion_be.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.giggi.osterianapulion_be.entity.Utente;
import com.giggi.osterianapulion_be.repository.UtenteRepository;
import com.giggi.osterianapulion_be.service.UtenteService;

@Service
@Transactional
@RequiredArgsConstructor
public class UtenteServiceImpl implements UtenteService {

    private final UtenteRepository utenteRepository;

    @Override
    public Utente save(Utente utente) {
        return utenteRepository.save(utente);
    }

    @Override
    public Utente update(Utente utente) {
        return utenteRepository.save(utente);
    }

    @Override
    public void deleteById(Long id) {
        utenteRepository.deleteById(id);
    }

    @Override
    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }

    @Override
    public Utente findById(Long id) {
        return utenteRepository.findById(id).orElse(null);
    }
}