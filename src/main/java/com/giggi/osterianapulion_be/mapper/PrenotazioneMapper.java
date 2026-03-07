package com.giggi.osterianapulion_be.mapper;

import org.mapstruct.Mapper;

import java.util.List;

import com.giggi.osterianapulion_be.entity.Prenotazione;
import com.giggi.osterianapulion_be.dto.request.prenotazione.PrenotazioneCreateRequestDTO;
import com.giggi.osterianapulion_be.dto.request.prenotazione.PrenotazioneUpdateRequestDTO;
import com.giggi.osterianapulion_be.dto.response.prenotazione.PrenotazioneFindDTO;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrenotazioneMapper {

    Prenotazione convert(PrenotazioneCreateRequestDTO dto);

    Prenotazione convert(PrenotazioneUpdateRequestDTO dto);

    @Mapping(target = "tavolo", source = "tavolo.nome")
    PrenotazioneFindDTO convert(Prenotazione entity);

    List<PrenotazioneFindDTO> convert(List<Prenotazione> entities);
}