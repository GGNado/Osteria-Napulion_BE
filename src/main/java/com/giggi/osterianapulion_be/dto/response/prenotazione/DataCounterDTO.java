package com.giggi.osterianapulion_be.dto.response.prenotazione;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
public class DataCounterDTO {
    private Map<LocalDate, Integer> dataCounter;
}
