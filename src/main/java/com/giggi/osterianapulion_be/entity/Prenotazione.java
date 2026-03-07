package com.giggi.osterianapulion_be.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCliente;

    @Column(nullable = false)
    private String cognomeCliente;

    @Column(nullable = false)
    private String emailCliente;

    private String telefonoCliente;

    @Column(nullable = false)
    private LocalDateTime dataOra;

    private int coperti;

    @Enumerated(EnumType.STRING)
    private StatoPrenotazione stato;

    @ManyToOne
    @JoinColumn(name = "tavolo_id", nullable = true)
    private Tavolo tavolo;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime dataCreazione;
}