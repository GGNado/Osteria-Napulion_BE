package com.giggi.osterianapulion_be.dto.response.Tavolo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TavoloFindAllDTO {
    private List<TavoloFindDTO> TavoloFindAllDTO;
}