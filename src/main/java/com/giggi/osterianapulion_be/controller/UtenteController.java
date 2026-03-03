package com.giggi.osterianapulion_be.controller;

import com.giggi.osterianapulion_be.dto.response.utente.UtenteFindAllDTO;
import com.giggi.osterianapulion_be.mapper.UtenteMapper;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.giggi.osterianapulion_be.entity.Utente;
import com.giggi.osterianapulion_be.service.UtenteService;

@RestController
@RequestMapping("/api/utenti")
@RequiredArgsConstructor
public class UtenteController {
    private final UtenteService utenteService;
    private final UtenteMapper utenteMapper;

    @GetMapping
    public ResponseEntity<UtenteFindAllDTO> getAllUtentes() {
        return ResponseEntity.ok(
                new UtenteFindAllDTO(
                        utenteService
                                .findAll().stream()
                                .map(utenteMapper::conver)
                                .toList())
        );
    }
}