package com.giggi.osterianapulion_be.dto.request.prenotazione;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class PrenotazioneCreateRequestDTO {
    private String nomeCliente;
    private String cognomeCliente;
    private String emailCliente;
    private String telefonoCliente;
    private LocalDateTime dataOra;
    private int coperti;
}