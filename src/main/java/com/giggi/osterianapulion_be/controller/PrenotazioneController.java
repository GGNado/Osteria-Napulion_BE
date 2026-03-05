package com.giggi.osterianapulion_be.controller;

import com.giggi.osterianapulion_be.dto.request.prenotazione.PrenotazioneCreateRequestDTO;
import com.giggi.osterianapulion_be.dto.response.prenotazione.PrenotazioneFindDTO;
import com.giggi.osterianapulion_be.mapper.PrenotazioneMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.giggi.osterianapulion_be.entity.Prenotazione;
import com.giggi.osterianapulion_be.service.PrenotazioneService;

@RestController
@RequestMapping("/api/prenotazioni")
@RequiredArgsConstructor
public class PrenotazioneController {
    private final PrenotazioneService prenotazioneService;
    private final PrenotazioneMapper prenotazioneMapper;

    @GetMapping
    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneService.findAll();
    }

    @PostMapping
    public ResponseEntity<PrenotazioneFindDTO> savePrenotazione(
            @RequestBody PrenotazioneCreateRequestDTO prenotazioneCreateRequestDTO) {
        return ResponseEntity.ok(
                prenotazioneMapper.convert(
                        prenotazioneService.save(
                                prenotazioneMapper.convert(prenotazioneCreateRequestDTO)
                        )
                )
        );
    }

}