package com.giggi.osterianapulion_be.dto.request.prenotazione;

import com.giggi.osterianapulion_be.entity.StatoPrenotazione;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PrenotazioneUpdateRequestDTO {
    private Long id;
    private StatoPrenotazione stato;
}