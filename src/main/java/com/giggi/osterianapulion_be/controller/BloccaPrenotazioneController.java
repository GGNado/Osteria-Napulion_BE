package com.giggi.osterianapulion_be.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.giggi.osterianapulion_be.entity.BloccaPrenotazione;
import com.giggi.osterianapulion_be.service.BloccaPrenotazioneService;

@RestController
@RequestMapping("/api/bloccaPrenotazioni")
@RequiredArgsConstructor
public class BloccaPrenotazioneController {
    private final BloccaPrenotazioneService bloccaPrenotazioneService;

    @GetMapping
    public List<BloccaPrenotazione> getAllBloccaPrenotazioni() {
        return bloccaPrenotazioneService.findAll();
    }
    // CRUD endpoints qui
}