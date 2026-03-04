package com.giggi.osterianapulion_be.service;

import java.util.List;

import com.giggi.osterianapulion_be.entity.Tavolo;

public interface TavoloService {
    Tavolo save(Tavolo tavolo);

    Tavolo update(Tavolo tavolo);

    void deleteById(Long id);

    List<Tavolo> findAll();

    Tavolo findById(Long id);
}