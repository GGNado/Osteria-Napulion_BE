package com.giggi.osterianapulion_be.validation;

import com.giggi.osterianapulion_be.dto.request.tavolo.TavoloUpdateSchemaDTO;
import com.giggi.osterianapulion_be.entity.Tavolo;
import com.giggi.osterianapulion_be.exception.tavolo.TavoloAttributoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TavoloValidator {
    public void validate(Tavolo tavolo){
        log.info("TavoloValidator");
        validateMaxMinPosti(tavolo);
        validateMaxPosti(tavolo);
        validateMinPosti(tavolo);
        log.info("Tavolo validato con successo");

    }

    private void validateMaxMinPosti(Tavolo tavolo){
        if (tavolo.getMassimoPosti() < tavolo.getMinimoPosti()){
            throw new TavoloAttributoException("Il massimo posti deve essere maggiore o uguale al minimo posti");
        }
    }

    private void validateMaxPosti(Tavolo tavolo){
        if (tavolo.getMassimoPosti() < 1){
            throw new TavoloAttributoException("Il massimo posti deve essere maggiore di 0");
        }
    }

    private void validateMinPosti(Tavolo tavolo){
        if (tavolo.getMinimoPosti() < 1){
            throw new TavoloAttributoException("Il minimo posti deve essere maggiore di 0");
        }
    }
}
