package com.giggi.osterianapulion_be.controller;

import com.giggi.osterianapulion_be.dto.request.tavolo.TavoloCreateRequestDTO;
import com.giggi.osterianapulion_be.dto.response.tavolo.TavoloFindDTO;
import com.giggi.osterianapulion_be.mapper.TavoloMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.giggi.osterianapulion_be.entity.Tavolo;
import com.giggi.osterianapulion_be.service.TavoloService;

@RestController
@RequestMapping("/api/tavoli")
@RequiredArgsConstructor
public class TavoloController {
    private final TavoloService tavoloService;
    private final TavoloMapper tavoloMapper;

    @GetMapping
    public List<Tavolo> getAllTavoli() {
        return tavoloService.findAll();
    }

    @PostMapping
    public ResponseEntity<TavoloFindDTO> saveTavolo(
            @RequestBody TavoloCreateRequestDTO tavoloCreateRequestDTO) {
        return ResponseEntity.ok(
                tavoloMapper.convert(
                        tavoloService.save(
                                tavoloMapper.convert(tavoloCreateRequestDTO)
                        ))
        );
    }
}