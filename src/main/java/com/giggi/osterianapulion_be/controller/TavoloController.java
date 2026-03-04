package com.giggi.osterianapulion_be.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.giggi.osterianapulion_be.entity.Tavolo;
import com.giggi.osterianapulion_be.service.TavoloService;

@RestController
@RequestMapping("/api/tavoli")
@RequiredArgsConstructor
public class TavoloController {
    private final TavoloService tavoloService;

    @GetMapping
    public List<Tavolo> getAllTavoli() {
        return tavoloService.findAll();
    }
    // CRUD endpoints qui
}