package com.giggi.osterianapulion_be.dto.response.prenotazione;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PrenotazioneFindDTO {
    private Long id;
    private String nomeCliente;
    private String nomeTavolo;

}