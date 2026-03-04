package com.giggi.osterianapulion_be.dto.response.Prenotazione;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PrenotazioneFindAllDTO {
    private List<PrenotazioneFindDTO> PrenotazioneFindAllDTO;
}