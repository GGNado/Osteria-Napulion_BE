package com.giggi.osterianapulion_be.entity;

import lombok.*;

import jakarta.persistence.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BloccaPrenotazioni")
public class BloccaPrenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataInizio;

    @Column(nullable = true)
    private LocalDate dataFine;

    @Column(nullable = false)
    private String motivo;

    @Column(nullable = false)
    private boolean attivo;

}