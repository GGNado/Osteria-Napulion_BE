package com.giggi.osterianapulion_be.dto.request.tavolo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TavoloCreateRequestDTO {
    private String nome;
    private int minimoPosti;
    private int massimoPosti;
    private boolean attivo;
}