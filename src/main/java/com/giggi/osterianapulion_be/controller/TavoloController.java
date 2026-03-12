package com.giggi.osterianapulion_be.controller;

import com.giggi.osterianapulion_be.dto.request.tavolo.TavoloCreateRequestDTO;
import com.giggi.osterianapulion_be.dto.request.tavolo.TavoloUpdateRequestDTO;
import com.giggi.osterianapulion_be.dto.request.tavolo.TavoloUpdateSchemaDTO;
import com.giggi.osterianapulion_be.dto.response.tavolo.TavoloFindAllDTO;
import com.giggi.osterianapulion_be.dto.response.tavolo.TavoloFindDTO;
import com.giggi.osterianapulion_be.mapper.TavoloMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
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
    public ResponseEntity<TavoloFindAllDTO> getAllTavoli() {
        return ResponseEntity.ok(
                new TavoloFindAllDTO(
                        tavoloService.findAll()
                                .stream()
                                .map(tavoloMapper::convert)
                                .toList()
                )
        );
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

    @PatchMapping("/update-schema")
    public ResponseEntity<TavoloFindAllDTO> updateTavolo(
            @RequestBody List<TavoloUpdateSchemaDTO> tavoli) {

        return ResponseEntity.ok(new TavoloFindAllDTO(
                tavoloService.updateSchema(
                                tavoli
                                        .stream()
                                        .map(tavoloMapper::convert)
                                        .toList())
                        .stream()
                        .map(tavoloMapper::convert)
                        .toList()
        ));
    }
}