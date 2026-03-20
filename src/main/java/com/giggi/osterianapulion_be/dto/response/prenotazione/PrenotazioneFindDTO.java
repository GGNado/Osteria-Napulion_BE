package com.giggi.osterianapulion_be.dto.response.prenotazione;

import com.giggi.osterianapulion_be.entity.StatoPrenotazione;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PrenotazioneFindDTO {
    private Long id;
    private String nomeCliente;
    private String dataOra;
    private StatoPrenotazione stato;
    private String telefonoCliente;
    private String tavolo;
    private Long tavoloId;
    private int coperti;

}