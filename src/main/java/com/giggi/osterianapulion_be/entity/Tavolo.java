package com.giggi.osterianapulion_be.entity;

import lombok.*;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Tavoli")
public class Tavolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private int minimoPosti;

    @Column(nullable = false)
    private int massimoPosti;

    @Column(nullable = false)
    private boolean attivo;

    @Column(nullable = false)
    private int x = 0;

    @Column(nullable = false)
    private int y = 0;

    @Transient
    private Boolean isNew = false;

    @OneToMany(mappedBy = "tavolo", cascade = CascadeType.ALL)
    private List<Prenotazione> prenotazioni = new ArrayList<>();


}