package com.giggi.osterianapulion_be.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.giggi.osterianapulion_be.entity.Tavolo;
import com.giggi.osterianapulion_be.repository.TavoloRepository;
import com.giggi.osterianapulion_be.service.TavoloService;

@Service
@Transactional
@RequiredArgsConstructor
public class TavoloServiceImpl implements TavoloService {

    private final TavoloRepository tavoloRepository;

    @Override
    public Tavolo save(Tavolo tavolo) {
        return tavoloRepository.save(tavolo);
    }

    @Override
    public Tavolo update(Tavolo tavolo) {
        return tavoloRepository.save(tavolo);
    }

    @Override
    public void deleteById(Long id) {
        tavoloRepository.deleteById(id);
    }

    @Override
    public List<Tavolo> findAll() {
        return tavoloRepository.findAll();
    }

    @Override
    public Tavolo findById(Long id) {
        return tavoloRepository.findById(id).orElse(null);
    }
}