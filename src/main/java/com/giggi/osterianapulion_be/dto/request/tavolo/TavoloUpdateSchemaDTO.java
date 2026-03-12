package com.giggi.osterianapulion_be.dto.request.tavolo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TavoloUpdateSchemaDTO {
    private Long id;
    private String nome;
    private int minimoPosti;
    private int massimoPosti;
    private boolean attivo;
    private int x;
    private int y;
    private Boolean isNew;
}
