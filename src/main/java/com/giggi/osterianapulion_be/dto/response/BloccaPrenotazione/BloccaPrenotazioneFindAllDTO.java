package com.giggi.osterianapulion_be.dto.response.BloccaPrenotazione;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BloccaPrenotazioneFindAllDTO {
    private List<BloccaPrenotazioneFindDTO> BloccaPrenotazioneFindAllDTO;
}