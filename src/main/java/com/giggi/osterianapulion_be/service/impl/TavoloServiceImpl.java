package com.giggi.osterianapulion_be.service.impl;

import com.giggi.osterianapulion_be.mapper.TavoloMapper;
import com.giggi.osterianapulion_be.validation.TavoloValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.giggi.osterianapulion_be.entity.Tavolo;
import com.giggi.osterianapulion_be.repository.TavoloRepository;
import com.giggi.osterianapulion_be.service.TavoloService;

@Service
@Transactional
@RequiredArgsConstructor
public class TavoloServiceImpl implements TavoloService {

    private final TavoloRepository tavoloRepository;
    private final TavoloValidator tavoloValidator;
    private final TavoloMapper tavoloMapper;

    @Override
    public Tavolo save(Tavolo tavolo) {
        return tavoloRepository.save(tavolo);
    }

    @Override
    public Tavolo update(Tavolo tavolo) {
        return tavoloRepository.save(tavolo);
    }

    @Override
    @Transactional
    public List<Tavolo> updateSchema(List<Tavolo> tavoli) {
        Map<Boolean, List<Tavolo>> partition = tavoli.stream()
                .collect(Collectors.partitioningBy(Tavolo::getIsNew));

        List<Tavolo> nuovi = partition.get(true).stream()
                .map(this::validateAndReturn)
                .toList();

        List<Tavolo> daAggiornare = partition.get(false).stream()
                .map(t -> {
                    validateAndReturn(t);
                    return tavoloRepository.findById(t.getId())
                            .map(esistente -> {
                                tavoloMapper.updateSchema(esistente, t);
                                return esistente;
                            })
                            .orElseThrow(() -> new EntityNotFoundException("Tavolo non trovato: " + t.getId()));
                })
                .toList();

        Set<Long> idInput = partition.get(false).stream()
                .map(Tavolo::getId)
                .collect(Collectors.toSet());

        tavoloRepository.findAll().stream()
                .filter(t -> !idInput.contains(t.getId()))
                .forEach(tavoloRepository::delete);

        List<Tavolo> daSalvare = new ArrayList<>(daAggiornare);
        daSalvare.addAll(nuovi);

        return tavoloRepository.saveAll(daSalvare);
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

    private Tavolo validateAndReturn(Tavolo t) {
        tavoloValidator.validate(t);
        return t;
    }
}